package com.prateem.jshell.commands;

import com.prateem.jshell.system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.driver.CommandParser
 * @see com.prateem.jshell.commands.Mkdir
 */
public class Cd extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
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
  public void run(String[] components) {

  }

}