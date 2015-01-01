package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.driver.CommandParser
 * @see com.prateemshrestha.jshell.commands.PushD
 */
public class PopD extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public PopD(Path path) {
    super(path);
  }

  /**
   * Return an array of command components that can be used to carry out a
   * successful run operation.
   *
   * @param command The command string to generate valid components from.
   * @return An array of command components that can be utilized to run.
   * @throws ValidationException If validating command components resulted in an error.
   */
  @Override
  public String[] getValidComponents(String command)
      throws ValidationException {
    if (!path.hasStoredLocation()) {
      throw new ValidationException("jshell: popd: directory stack empty");
    }

    return new String[0];
  }

  /**
   * Execute the implementation .
   *
   * @param args Arguments required to run the operation.
   * @throws FileSystemException If running of the operation caused an error.
   */
  @Override
  public void run(String[] args) throws FileSystemException {
    String lastLocation = path.getLastStoredLocation();
    path.goTo(lastLocation);
  }
}
