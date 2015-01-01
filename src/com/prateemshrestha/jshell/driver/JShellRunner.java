package com.prateemshrestha.jshell.driver;

import com.prateemshrestha.jshell.system.utilities.OutputCapturer;

/**
 * Takes messages and runs them to the JShell output!
 *
 * @author Prateem Shrestha
 */
public abstract class JShellRunner {

  /**
   * Switch to toggle output capturing.
   */
  private static boolean capture;

  /**
   * Set the output capturing switch.
   *
   * @param bool True if output capturing is to be turned on.
   */
  public static void setCapture(boolean bool) {
    capture = bool;
  }

  /**
   * Runs and displays a message to the shell.
   *
   * @param message The message to carry.
   * @param newLine Make message contents block-like or not.
   */
  public static void runMessage(String message, boolean newLine) {
    if (capture) {
      OutputCapturer.capture(message);
    } else {
      if (newLine) {
        System.out.println(message);
      } else {
        System.out.print(message);
      }
    }
  }

  /**
   * Runs and displays an error message to the shell.
   *
   * @param message The error message to carry.
   * @param newLine Make message contents block-like or not.
   */
  public static void runError(String message, boolean newLine) {
    // Avoiding use of stderr because stdout and stderr do not respect
    // the order in which they were called.
    if (newLine) {
      System.out.println(message);
    } else {
      System.out.print(message);
    }
  }

}
