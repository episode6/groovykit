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

  boolean isLessThan(String otherVersion) {
    return comparator.compare(versionString, otherVersion) < 0
  }

  boolean isLessThanEquals(String otherVersion) {
    return comparator.compare(versionString, otherVersion) <= 0
  }

  boolean isGreaterThan(String otherVersion) {
    return comparator.compare(versionString, otherVersion) > 0
  }

  boolean isGreaterThanEquals(String otherVersion) {
    return comparator.compare(versionString, otherVersion) >= 0
  }

  boolean isEqualTo(String otherVersion) {
    return comparator.compare(versionString, otherVersion) == 0
  }
}
