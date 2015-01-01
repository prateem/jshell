package com.prateemshrestha.jshell.commands;

import com.prateemshrestha.jshell.system.utilities.Path;

/**
 * Created by prateem on 01/01/15.
 */
public class Mv extends Relocate {

  /**
   * Constructor initializes with the unique Path of the current JShell that is
   * running.
   *
   * @param path Path to utilize or manipulate.
   */
  public Mv(Path path) {
    super(path);
  }

}
