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
    File root = temporaryFolder.root

    when:
    File deepSubdir = root.newFolder("dir1", "dir2", "dir3")
    File singleSubDir = root.newFolder("singleSubDir")

    then:
    assertValidDirectory(deepSubdir, "/dir1/dir2/dir3")
    assertValidDirectory(singleSubDir, "/singleSubDir")
  }

  def "test newFolderFromPackage method"() {
    given:
    File root = temporaryFolder.root

    when:
    File realPackageSubdir = root.newFolderFromPackage("com.testing.cool")
    File singlePackageSubdir = root.newFolderFromPackage("nice")

    then:
    assertValidDirectory(realPackageSubdir, "/com/testing/cool")
    assertValidDirectory(singlePackageSubdir, "/nice")
  }

  private boolean assertValidDirectory(File file, String expectedPath, File expectedRoot = temporaryFolder.root) {
    assert file.path == expectedRoot.path + expectedPath
    assert file.isDirectory()
    assert file.exists()
    return true
  }
}
