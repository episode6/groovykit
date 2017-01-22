package com.episode6.hackit.groovykit.versions

/**
 * Exception thrown when trying to parse/compare an invalid version string
 */
class InvalidVersionException extends RuntimeException {
  InvalidVersionException() {
  }

  InvalidVersionException(String message) {
    super(message)
  }

  InvalidVersionException(String messaage, Throwable cause) {
    super(messaage, cause)
  }

  InvalidVersionException(Throwable cause) {
    super(cause)
  }
}
