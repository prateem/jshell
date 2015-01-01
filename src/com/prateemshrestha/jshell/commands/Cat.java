package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.driver.JShellRunner;
import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.Directory;
import com.prateemshrestha.jshell.system.File;
import com.prateemshrestha.jshell.system.utilities.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Write file contents to the shell.
 */
public class Cat extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public Cat(Path path) {
    super(path);
  }

  /**
   * Ensure that each argument supplied is a valid file.
   *
   * @param command The command string to generate valid components from.
   * @return An array of paths to existing File objects in the system.
   * @throws ValidationException If no arguments are given.
   */
  @Override
  public String[] getValidComponents(String command)
      throws ValidationException {
    String[] components = stripWhitespace(command);

    if (components.length < 2) {
      throw new ValidationException("cat: No file specified");
    }

    List<String> validPaths = new ArrayList<String>();

    String filePath;
    for (int i = 1; i < components.length; i++) {
      filePath = components[i];
      if (fileExists(filePath)) {
        validPaths.add(filePath);
      }
    }

    return validPaths.toArray(new String[validPaths.size()]);
  }

  /**
   * Check that a file at the given path exists.
   *
   * @param filePath The path to check for the existence of a File object.
   * @return True if and only if the given path leads to an existing File.
   */
  private boolean fileExists(String filePath) {
    if (!path.isValid(File.class, filePath)) {
      String message = String.format(
          "cat: %s: %s",
          filePath,
          path.isValid(Directory.class, filePath)
              ? "Is a directory" : "No such file or directory"
      );

      JShellRunner.runError(message, true);
      return false;
    }

    return true;
  }

  @Override
  public void run(String[] args) throws FileSystemException {
    File file;
    for (String filePath : args) {
      file = (File) path.get(filePath);
      JShellRunner.runMessage(file.getContents(), true);
    }
  }

}
