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

}
