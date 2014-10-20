package commands;

import exceptions.ValidationException;
import system.FileSystem;

public abstract class Command {

  final private FileSystem fileSystem;

  /**
   * Constructor initializes with a file system.
   *
   * @param fileSystem File system to do work on.
   */
  public Command(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * Validate a command and return an array of command components if successful.
   *
   * @param command A String to validate as a command.
   * @return String[] of command components.
   */
  public abstract String[] validate(String command);

  /**
   * Run a validated command.
   *
   * @param validCommand Validated command components to run with.
   */
  public abstract void runCommand(String[] validCommand);

  /**
   * Split a String on all whitespace.
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
