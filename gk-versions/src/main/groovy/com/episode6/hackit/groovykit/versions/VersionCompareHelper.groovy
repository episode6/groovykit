package com.episode6.hackit.groovykit.versions

/**
 * Helper class for comparing versions directly from the String representation of it
 */
class VersionCompareHelper {
  final String versionString
  final VersionComparator comparator

  VersionCompareHelper(String versionString) {
    this.versionString = versionString
    this.comparator = new VersionComparator()
  }

  boolean isLessThen(String otherVersion) {
    return comparator.compare(versionString, otherVersion) < 0
  }

  boolean isLessThenEquals(String otherVersion) {
    return comparator.compare(versionString, otherVersion) <= 0
  }

  boolean isGreaterThen(String otherVersion) {
    return comparator.compare(versionString, otherVersion) > 0
  }

  boolean isGreaterThenEquals(String otherVersion) {
    return comparator.compare(versionString, otherVersion) >= 0
  }

  boolean isEqualTo(String otherVersion) {
    return comparator.compare(versionString, otherVersion) == 0
  }
}
