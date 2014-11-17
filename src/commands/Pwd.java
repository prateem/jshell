package commands;

import system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see driver.CommandParser
 * @see commands.Cd
 */
public class Pwd extends Command {

  /**
   * TODO
   *
   * @param path TODO
   */
  public Pwd(Path path) {
    super(path);
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
   * @param components Validated command components to run with.
   */
  @Override
  public void runCommand(String[] components) {

  }

}
