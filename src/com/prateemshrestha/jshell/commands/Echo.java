package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.driver.JShellRunner;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * Print any given arguments to the shell.
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 */
public class Echo extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public Echo(Path path) {
    super(path);
  }

  /**
   * Ensure that some arguments were passed to the command.
   *
   * @param command The command string to generate valid components from.
   * @return An array of strings to be echoed to the shell.
   * @throws ValidationException If no arguments were given to the command.
   */
  @Override
  public String[] getValidComponents(String command)
      throws ValidationException {
    String[] components = stripWhitespace(command);

    if (components.length < 2) {
      throw new ValidationException("echo: invalid command");
    }

    return components;
  }

  /**
   * Output provided arguments to the shell.
   *
   * @param args Arguments to be output to the shell.
   */
  @Override
  public void run(String[] args) {
    StringBuilder outputBuilder = new StringBuilder();

    for (int i = 1; i < args.length; i++) {
      // Cannot just utilize trim because a string desired to be output may
      // terminate with a whitespace.
      String echoString = String.format("%s%s",
          args[i],
          i == args.length - 1 ? "" : " ");
      outputBuilder.append(echoString);
    }

    // Print the echo statement to the shell.
    JShellRunner.runMessage(outputBuilder.toString(), true);
  }

}
