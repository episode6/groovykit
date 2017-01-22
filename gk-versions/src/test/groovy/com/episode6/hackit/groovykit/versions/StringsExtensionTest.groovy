package com.episode6.hackit.groovykit.versions

import spock.lang.Specification

/**
 * Tests methods added by {@link StringsExtension}
 */
class StringsExtensionTest extends Specification {

  def "simple compare test"() {
    when:
    String lowerVersion = "0.1.5"
    String higherVersion = "0.2.4"

    then:
    assertDiffVersionsCompareCorrectly(lowerVersion, higherVersion)
  }

  def "test multi-digit compare"() {
    when:
    String lowerVersion = "1.4.4"
    String higherVersion = "1.15.3"

    then:
    assertDiffVersionsCompareCorrectly(lowerVersion, higherVersion)
  }

  def "test weird digit change"() {
    when:
    String lowerVersion = "001.3.75"
    String higherVersion = "2.050.75"

    then:
    assertDiffVersionsCompareCorrectly(lowerVersion, higherVersion)
  }

  private static boolean assertDiffVersionsCompareCorrectly(String lowerVersion, String higherVersion) {
    assertSameVersionsCompareCorrectly(lowerVersion)
    assertSameVersionsCompareCorrectly(higherVersion)

    assert lowerVersion.asVersion().isLessThen(higherVersion)
    assert lowerVersion.asVersion().isLessThenEquals(higherVersion)
    assert !lowerVersion.asVersion().isGreaterThen(higherVersion)
    assert !lowerVersion.asVersion().isGreaterThenEquals(higherVersion)
    assert !lowerVersion.asVersion().isEqualTo(higherVersion)

    assert higherVersion.asVersion().isGreaterThen(lowerVersion)
    assert higherVersion.asVersion().isGreaterThenEquals(higherVersion)
    assert !higherVersion.asVersion().isLessThen(lowerVersion)
    assert !higherVersion.asVersion().isLessThenEquals(lowerVersion)
    assert !higherVersion.asVersion().isEqualTo(lowerVersion)

    return true
  }

  private static boolean assertSameVersionsCompareCorrectly(String version) {
    assert version.asVersion().isLessThenEquals(version)
    assert version.asVersion().isGreaterThenEquals(version)
    assert version.asVersion().isEqualTo(version)
    return true
  }
}
