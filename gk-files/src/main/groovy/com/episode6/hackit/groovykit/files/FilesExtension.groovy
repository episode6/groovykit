package com.episode6.hackit.groovykit.files

/**
 * Extension methods for the File class that I've found useful
 */
class FilesExtension {

  static final File newFolder(final File self, String... paths) {
    if (paths.length < 1) {
      throw new IllegalArgumentException("can't create folder with empty path")
    }

    File dir = self
    paths.each { String path ->
      dir = new File(dir, path)
      dir.mkdir()
    }
    return dir
  }

  static final File newFile(final File self, String... paths) {
    if (paths.length < 1) {
      throw new IllegalArgumentException("can't create file with empty path")
    }

    String filename = paths[paths.length-1]
    File dir = self;
    if (paths.length > 2) {
      String[] folderPaths = paths[0..paths.length-2]
      dir = newFolder(dir, folderPaths)
    }
    return new File(dir, filename)
  }

  static File newFolderFromPackage(final File self, String packageName) {
    return newFolder(self, (String[])packageName.tokenize('.').toArray())
  }

  static asXml(final File self) {
    return new XmlSlurper().parse(self)
  }

  static asJson(final File self) {
    return new groovy.json.JsonSlurper().parse(self)
  }
}