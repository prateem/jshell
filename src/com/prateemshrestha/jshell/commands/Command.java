package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.utilities.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parent class for all commands. Outlines basic command functionality
 * and provides utility methods.
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 * @see com.prateemshrestha.jshell.commands.Cd
 * @see com.prateemshrestha.jshell.commands.Mkdir
 * @see com.prateemshrestha.jshell.commands.Pwd
 * @see com.prateemshrestha.jshell.commands.PopD
 */
public abstract class Command {

  /**
   * TODO
   */
  protected Path path;

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public Command(Path path) {
    this.path = path;
  }

  /**
   * Return an array of command components that can be used to carry out a
   * successful run operation.
   *
   * @param command The command string to generate valid components from.
   * @return An array of command components that can be utilized to run.
   * @throws ValidationException If validating command components resulted in
   * an error.
   */
  public abstract String[] getValidComponents(String command)
      throws ValidationException;

  /**
   * Execute the implementation .
   *
   * @param args Arguments required to run the operation.
   * @throws FileSystemException If running of the operation caused an error.
   */
  public abstract void run(String[] args) throws FileSystemException;

  /**
   * Strip all whitespace from a command input and return a String array.
   *
   * @param command The command to strip whitespace from.
   * @return A string array made up of command components.
   */
  public static String[] stripWhitespace(String command) {
    Pattern pattern = Pattern.compile("\"[^\"]+\"|[^\\s]+");
    Matcher matcher = pattern.matcher(command.trim());

    List<String> matches = new ArrayList<String>();
    while (matcher.find()) {
      String match = matcher.group();
      if (match.matches("\"[^\"]+\"")) {
        match = match.substring(1, match.length() - 1);
      }

      matches.add(match);
    }

    return matches.toArray(new String[matches.size()]);
  }

}
