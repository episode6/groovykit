package com.episode6.hackit.groovykit.versions

/**
 * Extension methods Strings relating to version-compares
 */
class StringsExtension {

  static final VersionCompareHelper asVersion(final String self) {
    return new VersionCompareHelper(self)
  }
}