package exceptions;

/**
 * Carries exception messages that relate to file system errors.
 *
 * @author Prateem Shrestha
 * @see exceptions.ValidationException
 */
public class FileSystemException extends JShellException {

  /**
   * Accepts a file system-related error message and carries it.
   *
   * @param message The file system-related error message to carry.
   */
  public FileSystemException(String message) {
    super(message);
  }
  
}
