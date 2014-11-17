package commands;

import system.FileSystem;

public class Pwd extends Command {
  /**
   * Constructor initializes with a file system.
   *
   * @param fileSystem File system to do work on.
   */
  public Pwd(FileSystem fileSystem) {
    super(fileSystem);
  }

  /**
   * Validate a "pwd" command and return an array of command components.
   *
   * @param command A String to validate as a command.
   * @return String[] of valid "pwd" command components.
   */
  @Override
  public String[] getValidComponents(String command) {
    return new String[0];
  }

  /**
   * Run a validated "pwd" command.
   *
   * @param validCommand Validated command components to run with.
   */
  @Override
  public void runCommand(String[] validCommand) {

  }
}
