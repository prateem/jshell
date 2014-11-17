package exceptions;

/**
 * Carries exception messages that relate to validation errors.
 *
 * @author Prateem Shrestha
 * @see exceptions.FileSystemException
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
