package com.prateemshrestha.jshell.exceptions;

/**
 * Carries exception messages that relate to the JShell's environment.
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.exceptions.FileSystemException
 * @see com.prateemshrestha.jshell.exceptions.ValidationException
 */
public abstract class JShellException extends Exception {

  /**
   * Accepts an error message related to JShell and carries it.
   *
   * @param message The JShell-related error message to carry.
   */
  public JShellException(String message) {
    super(message);
  }

}
