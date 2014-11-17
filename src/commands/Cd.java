package commands;

import system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see driver.CommandParser
 * @see commands.Mkdir
 */
public class Cd extends Command {

  /**
   * Constructor initializes with a file system.
   *
   * @param path File system to do work on.
   */
  public Cd(Path path) {
    super(path);
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
   * @param components Validated command components to run with.
   */
  @Override
  public void runCommand(String[] components) {

  }

}
