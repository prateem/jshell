package commands;

import exceptions.FileSystemException;
import exceptions.ValidationException;
import system.FileSystem;
import system.utilities.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see driver.CommandParser
 * @see commands.Cd
 * @see commands.Mkdir
 * @see commands.Pwd
 */
public abstract class Command {

  /**
   * TODO
   */
  private Path path;

  /**
   * Constructor initializes with a file system.
   *
   * @param path Path to utilize or manipulate.
   */
  public Command(Path path) {
    this.path = path;
  }

  /**
   * Validate a command and return an array of command components if successful.
   *
   * @param command A String to validate as a command.
   * @return String[] of command components.
   * @throws ValidationException
   */
  public abstract String[] getValidComponents(String command)
      throws ValidationException;

  /**
   * Run a validated command.
   *
   * @param components Validated command components to run with.
   * @throws FileSystemException
   */
  public abstract void runCommand(String[] components) throws
      FileSystemException;

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

  /**
   * Throw an exception informing the user that the last command was invalid.
   *
   * @throws ValidationException
   */
  public static void throwInvalidCommandError() throws ValidationException {
    throw new ValidationException("Invalid command, please try again.");
  }

}
