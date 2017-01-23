package com.episode6.hackit.groovykit.versions

import spock.lang.Specification

/**
 * Tests {@link VersionComparator}
 */
class VersionComparatorTest extends Specification {

  final VersionComparator versionComparator = new VersionComparator()

  def "test sorting a list of versions"() {
    given:
    List<String> versions = [
        "10.5.27",
        "02.3",
        "4.7.1",
        "4.7",
        "2.03.1",
        "2.3-SNAPSHOT"
    ]

    when:
    versions.sort(versionComparator)

    then:
    versions.get(0) == "2.3-SNAPSHOT"
    versions.get(1) == "02.3"
    versions.get(2) == "2.03.1"
    versions.get(3) == "4.7"
    versions.get(4) == "4.7.1"
    versions.get(5) == "10.5.27"
  }

  def "test weird version compare"() {
    given:
    String lowerVersion = "1.1-groovy-2.4-rc-2"
    String higherVersion = "1.1-groovy-2.4-rc-3"

    when:
    int compareResult = versionComparator.compare(lowerVersion, higherVersion)
    int compareResultInverse = versionComparator.compare(higherVersion, lowerVersion)

    then:
    compareResult < 0
    compareResultInverse > 0
  }

  def "test snapshot compare"() {
    given:
    String lowerVersion = "2.8.17-SNAPSHOT"
    String higherVersion = "2.08.17"

    when:
    int compareResult = versionComparator.compare(lowerVersion, higherVersion)
    int compareResultInverse = versionComparator.compare(higherVersion, lowerVersion)

    then:
    compareResult < 0
    compareResultInverse > 0
  }

  def "test build.snapshot"() {
    given:
    String lowerVersion = "2.1.0.BUILD-SNAPSHOT"
    String higherVersion = "2.1.0"

    when:
    int compareResult = versionComparator.compare(lowerVersion, higherVersion)
    int compareResultInverse = versionComparator.compare(higherVersion, lowerVersion)

    then:
    compareResult < 0
    compareResultInverse > 0
  }

  def "test trailing zeroes non-equal"() {
    given:
    String lowerVersion = "2.1.0"
    String higherVersion = "2.1.0.0.0.0.0.1"

    when:
    int compareResult = versionComparator.compare(lowerVersion, higherVersion)
    int compareResultInverse = versionComparator.compare(higherVersion, lowerVersion)

    then:
    compareResult < 0
    compareResultInverse > 0
  }

  def "test trailing zeroes with ambiguous test"() {
    given:
    String lowerVersion = "2.1.0"
    String higherVersion = "2.1.0.0-groovy-0.0.0.1"

    when:
    int compareResult = versionComparator.compare(lowerVersion, higherVersion)
    int compareResultInverse = versionComparator.compare(higherVersion, lowerVersion)

    then:
    compareResult < 0
    compareResultInverse > 0
  }

  def "test trailing zeroes equality"() {
    given:
    String version1 = "2.1.0"
    String version2 = "2.1.0.0.0.0.0.0"

    when:
    int compareResult = versionComparator.compare(version1, version2)
    int compareResultInverse = versionComparator.compare(version2, version1)

    then:
    compareResult == 0
    compareResultInverse == 0
  }

  def "test trailing ambuous text equality"() {
    given:
    String version1 = "2.1.0"
    String version2 = "2.1.0.0.0-groovy-0.0.0"

    when:
    int compareResult = versionComparator.compare(version1, version2)
    int compareResultInverse = versionComparator.compare(version2, version1)

    then:
    compareResult == 0
    compareResultInverse == 0
  }
}
