/**
 * Originally lifted from grails source:
 * https://github.com/grails/grails-core/blob/master/grails-bootstrap/src/main/groovy/grails/plugins/VersionComparator.groovy
 *
 * However, this has now been modified so much that it barely resembles the original source.
 *
 * Original Copyright notice included below:
 *
 * Copyright 2004-2005 Graeme Rocher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.episode6.hackit.groovykit.versions

import org.gradle.api.Nullable

/**
 * A comparator capable of comparing version strings
 */
class VersionComparator implements Comparator<String> {
  private static final Map<String, Integer> SPECIAL_MEANING = [
      dev: -2,
      snapshot: -1,
      rc: 1,
      final: 2]

  int compare(String o1, String o2) {
    if (o1 == o2) {
      return 0
    } else if (o1 == '*') {
      return 1
    } else if (o2 == '*') {
      return -1
    }

    def parts1 = o1.split(/\W/)
    def parts2 = o2.split(/\W/)

    int commonIndecies = Math.min(parts1.length, parts2.length)
    int i = 0
    for (; i < commonIndecies; i++) {
      if (parts1[i] == parts2[i]) {
        continue
      }

      Integer num1 = convertPartToNumber(parts1[i])
      Integer num2 = convertPartToNumber(parts2[i])

      if (num1 == null && num2 == null) {
        // we found two different strings that don't have special meanings, just compare them as strings
        return parts1[i] <=> parts2[i]
      } else if (num1 != null && num2 == null) {
        // left-side is a number, right side isn't
        return 1
      } else if (num1 == null && num2 != null) {
        // right-side is a number, left-side isn't
        return -1
      } else if (num1 != num2) {
        // both sides are non-equal numbers
        return num1 <=> num2
      }
    }

    // if everything has been equal up to this point, find the next non-zero
    // number in the longer versionString and compare it to 0
    if (parts1.length > parts2.length) {
      return findNextNonZeroNumber(parts1, i) <=> 0
    } else if (parts2.length > parts1.length) {
      return 0 <=> findNextNonZeroNumber(parts2, i)
    }
    return 0
  }

  private static int findNextNonZeroNumber(String[] parts, int startIndex) {
    for (int i = startIndex; i < parts.length; i++) {
      Integer number = convertPartToNumber(parts[i])
      if (number != null && number != 0) {
        return number
      }
    }
    return 0
  }

  /**
   * Converts the provided string to an Integer (or null), taking into account the {@link #SPECIAL_MEANING} map
   * @return An integer if the string is either parsable or can be translated via {@lin #SPECIAL_MEANING}
   */
  private static @Nullable Integer convertPartToNumber(String part) {
    part = part.trim()
    if (isNumber(part)) {
      return part.toInteger()
    }

    return SPECIAL_MEANING.get(part.toLowerCase())
  }

  private static boolean isNumber(String str) {
    return str ==~ /\d+/
  }
}