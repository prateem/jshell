package com.prateem.jshell.commands;

import com.prateem.jshell.exceptions.FileSystemException;
import com.prateem.jshell.exceptions.ValidationException;
import com.prateem.jshell.system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.driver.CommandParser
 * @see com.prateem.jshell.commands.Cd
 */
public class Mkdir extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
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
  public void run(String[] components) throws FileSystemException {

  }

}
