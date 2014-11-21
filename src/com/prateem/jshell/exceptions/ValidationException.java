package com.prateem.jshell.exceptions;

/**
 * Carries exception messages that relate to validation errors.
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.exceptions.FileSystemException
 */
public class ValidationException extends JShellException {

  /**
   * Accepts a validation-related error message and carries it.
   *
   * @param message The validation error message to carry.
   */
  public ValidationException(String message) {
    super(message);
  }

}
