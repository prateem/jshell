package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * Created by prateem on 01/01/15.
 */
public class Man extends Command {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public Man(Path path) {
    super(path);
  }

  @Override
  public String[] getValidComponents(String command)
      throws ValidationException {
    return new String[0];
  }

  @Override
  public void run(String[] args) throws FileSystemException {

  }

}
