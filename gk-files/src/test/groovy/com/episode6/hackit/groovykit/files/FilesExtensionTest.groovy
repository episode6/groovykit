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

  def "test newFile method"() {
    given:
    File root = temporaryFolder.root

    when:
    File testFileInSubDir = root.newFile("src", "main", "testFile.txt")
    testFileInSubDir.text = "test file in subdir"
    File testFileInRootDir = root.newFile("testinroot.txt")
    testFileInRootDir.text = "test file in root"

    then:
    assertValidFile(testFileInSubDir, "/src/main/testFile.txt")
    testFileInSubDir.text == "test file in subdir"
    assertValidFile(testFileInRootDir, "/testinroot.txt")
    testFileInRootDir.text == "test file in root"
  }

  def "test asXml method"() {
    given:
    File testXmlFile = temporaryFolder.newFile("testing.xml")
    testXmlFile << """
<root>
  <firstelm>hello</firstelm>
  <secondelm>
    <innerelm>hi again</innerelm>
  </secondelm>
</root>
"""

    when:
    def xml = testXmlFile.asXml()

    then:
    xml.firstelm.text() == "hello"
    xml.secondelm.innerelm.text() == "hi again"
  }

  def "test asJson method"() {
    given:
    File testJsonFile = temporaryFolder.newFile("testing.json")
    testJsonFile << """
{
  "firstkey": "hello",
  "secondkey": {
    "innerkey": "hi again"
  }
}
"""

    when:
    def json = testJsonFile.asJson()

    then:
    json.firstkey == "hello"
    json.secondkey.innerkey == "hi again"
  }

  private boolean assertValidDirectory(File file, String expectedPath, File expectedRoot = temporaryFolder.root) {
    assert file.path == expectedRoot.path + expectedPath
    assert file.isDirectory()
    assert file.exists()
    return true
  }

  private boolean assertValidFile(File file, String expectedPath, File expectedRoot = temporaryFolder.root) {
    assert file.path == expectedRoot.path + expectedPath
    assert !file.isDirectory()
    assert file.exists()
    return true
  }
}
