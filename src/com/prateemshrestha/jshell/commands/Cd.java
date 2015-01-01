package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.Directory;
import com.prateemshrestha.jshell.system.File;
import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * Responsible for changing the working directory of the shell.
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 * @see com.prateemshrestha.jshell.commands.Mkdir
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
   * @throws ValidationException If no directory or path was specified, or if
   * the specified directory or path does not lead to an existing folder.
   */
  @Override
  public String[] getValidComponents(String command)
      throws ValidationException {
    String[] components = stripWhitespace(command);

    if (components.length < 2) {
      throw new ValidationException("jshell: cd: No path specified");
    } else if (!path.isValid(Directory.class, components[1])) {
      throw new ValidationException(String.format(
          "jshell: cd: %s: %s", components[1],
          path.isValid(File.class, components[1]) ?
              "No such directory" : "No such file or directory"
      ));
    }

    return components;
  }

  /**
   * Run a validated "cd" command.
   *
   * @param components Validated command components to run with.
   * @throws FileSystemException If obtaining a Directory by name fails.
   */
  @Override
  public void run(String[] components) throws FileSystemException {
    path.goTo(components[1]);
  }

}
