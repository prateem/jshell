package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.utilities.Path;

/**
 *
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 * @see com.prateemshrestha.jshell.commands.PopD
 */
public class PushD extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public PushD(Path path) {
    super(path);
  }

  /**
   * Validate a command and return an array of command components if successful.
   *
   * @param command A String to validate as a command.
   * @return String[] of command components.
   * @throws ValidationException
   */
  @Override
  public String[] getValidComponents(String command) throws ValidationException {
    return new String[0];
  }

  /**
   * Run a validated command.
   *
   * @param components Validated command components to run with.
   * @throws FileSystemException
   */
  @Override
  public void run(String[] components) throws FileSystemException {
    path.rememberLocation();
    path.goTo(components[1]);
  }

}
