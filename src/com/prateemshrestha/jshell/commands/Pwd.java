package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.driver.JShellRunner;
import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 * @see com.prateemshrestha.jshell.commands.Cd
 */
public class Pwd extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
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
  public void run(String[] components) {
    String location = path.getAbsolutePath();
    JShellRunner.runMessage(location, true);
  }

}
