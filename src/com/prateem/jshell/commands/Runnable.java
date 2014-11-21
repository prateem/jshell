package com.prateem.jshell.commands;

/**
 * Created by prateem on 21/11/14.
 */
public interface Runnable {

  /**
   * Execute the implementation .
   *
   * @param args Arguments required to run the operation.
   * @throws Exception If running of the operation caused an error.
   */
  void run(String[] args) throws Exception;

}
