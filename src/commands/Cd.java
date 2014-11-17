package commands;

import system.FileSystem;

public class Cd extends Command {
  /**
   * Constructor initializes with a file system.
   *
   * @param fileSystem File system to do work on.
   */
  public Cd(FileSystem fileSystem) {
    super(fileSystem);
  }

  /**
   * Validate a "cd" command.
   *
   * @param command A String to validate as a command.
   * @return String[] of valid "cd" command components.
   */
  @Override
  public String[] getValidComponents(String command) {
    return new String[0];
  }

  /**
   * Run a validated "cd" command.
   *
   * @param validCommand Validated command components to run with.
   */
  @Override
  public void runCommand(String[] validCommand) {

  }
}