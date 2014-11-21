package com.prateem.jshell.commands;

/**
 *
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.commands.Runnable
 */
public interface RunnableCommand extends Runnable {

  /**
   * Return an array of command components that can be used to carry out a
   * successful run operation.
   *
   * @param command The command string to generate valid components from.
   * @return An array of command components that can be utilized to run.
   * @throws Exception If validating command components resulted in an error.
   */
  String[] getValidComponents(String command) throws Exception;

}
