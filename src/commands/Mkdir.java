package commands;

import exceptions.FileSystemException;
import exceptions.ValidationException;
import system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see driver.CommandParser
 * @see commands.Cd
 */
public class Mkdir extends Command {

  /**
   * TODO
   *
   * @param path TODO
   */
  public Mkdir(Path path) {
    super(path);
  }

  /**
   * Validate a "mkdir" command.
   *
   * @param command A String to validate as a command.
   * @return Valid "mkdir" command components.
   */
  @Override
  public String[] getValidComponents(String command) throws ValidationException {
    return new String[0];
  }

  /**
   * Run a "mkdir" command.
   *
   * @param components Validated command components to run with.
   */
  @Override
  public void runCommand(String[] components) throws FileSystemException {

  }

}
