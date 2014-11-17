package commands;

import exceptions.FileSystemException;
import exceptions.ValidationException;
import system.FileSystem;
import system.path.Path;

public abstract class Command {

  private final FileSystem fileSystem = FileSystem.getFileSystem();
  private Path path;

  /**
   * Constructor initializes with a file system.
   *
   * @param path Path to utilize or manipulate.
   */
  public Command(Path path) {
    this.path = path;
  }

  /**
   * Validate a command and return an array of command components if successful.
   *
   * @param command A String to validate as a command.
   * @return String[] of command components.
   * @throws ValidationException
   */
  public abstract String[] getValidComponents(String command) throws ValidationException;

  /**
   * Run a validated command.
   *
   * @param validCommand Validated command components to run with.
   * @throws FileSystemException
   */
  public abstract void runCommand(String[] validCommand) throws
      FileSystemException;

  /**
   * Split a String on all whitespace.
   *
   * @param command A string to split on whitespace.
   * @return String[] of command components.
   */
  public static String[] splitOnWhitespace(String command) {
    return command.split("[ \\t]+");
  }

  /**
   * Throw an exception informing the user that the last command was invalid.
   *
   * @throws ValidationException
   */
  public static void throwInvalidCommandError() throws ValidationException {
    throw new ValidationException("Invalid command, please try again.");
  }

}
