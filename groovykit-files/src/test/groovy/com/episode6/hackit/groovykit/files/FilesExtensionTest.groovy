package com.episode6.hackit.groovykit.files

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

/**
 * Tests methods added by FilesExtension
 */
class FilesExtensionTest extends Specification {

  @Rule final TemporaryFolder temporaryFolder = new TemporaryFolder()

  def "test newFolder method"() {
    given:
    File rootDir = temporaryFolder.root

    when:
    File deepSubdir = rootDir.newFolder("dir1", "dir2", "dir3")
    File singleSubDir = rootDir.newFolder("singleSubDir")

    then:
    deepSubdir.path == rootDir.path + "/dir1/dir2/dir3"
    deepSubdir.isDirectory()
    deepSubdir.exists()
    singleSubDir.path == rootDir.path + "/singleSubDir"
  }
}
