package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.driver.JShellRunner;
import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.Directory;
import com.prateemshrestha.jshell.system.FileSystemObject;
import com.prateemshrestha.jshell.system.utilities.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 * @see com.prateemshrestha.jshell.commands.Cd
 * @see com.prateemshrestha.jshell.system.Directory
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
  public String[] getValidComponents(String command)
      throws ValidationException {
    String[] components = stripWhitespace(command);

    if (components.length < 2) {
      throw new ValidationException("mkdir: missing operand");
    }

    List<String> valid = new ArrayList<String>();

    String directoryPath;
    for (int i = 1; i < components.length; i++) {
      directoryPath = components[i];

      if (directoryCanBeMade(directoryPath)) {
        valid.add(directoryPath);
      }
    }

    return valid.toArray(new String[valid.size()]);
  }

  /**
   * Identify any problems with making a Directory using the specified path.
   * Ensures that the Directory name is valid, and that the location to make the
   * Directory in exists. Finally, ensures that the Directory does not already
   * exist.
   *
   * @param directoryPath The desired path to the Directory to be made.
   */
  private boolean directoryCanBeMade(String directoryPath) {
    String[] pathComponents = Path.getPathComponents(directoryPath);

    // Make sure that we can get to this location to make the folder.
    if (!path.isValid(Directory.class, pathComponents[0])) {
      JShellRunner.runError(String.format(
          "mkdir: cannot create directory '%s': No such file or directory",
          directoryPath), true);
      return false;
    }

    // Make sure the directory doesn't already exist.
    if (path.isValid(FileSystemObject.class, directoryPath)) {
      JShellRunner.runError(String.format(
          "mkdir: cannot create directory '%s': File or folder exists",
          directoryPath), true);
      return false;
    }

    // Finally, make sure that the given desired directory name is valid.
    if (!FileSystemObject.canBeNamed(pathComponents[1])) {
      JShellRunner.runError(String.format(
          "mkdir: cannot create directory '%s': Invalid name", directoryPath
      ), true);
      return false;
    }

    return true;
  }

  /**
   * Run a "mkdir" command.
   *
   * @param components Validated command components to run with.
   */
  @Override
  public void run(String[] components) throws FileSystemException {
    for (String directoryPath : components) {
      String[] pathComponents = Path.getPathComponents(directoryPath);
      Directory location = (Directory) path.get(pathComponents[0]);
      location.add(new Directory(pathComponents[1]));
    }
  }

}
