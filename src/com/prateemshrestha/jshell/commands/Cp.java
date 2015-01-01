package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * Created by prateem on 01/01/15.
 */
public class Cp extends Relocate {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public Cp(Path path) {
    super(path);
  }

}
