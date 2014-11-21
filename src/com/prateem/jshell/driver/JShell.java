package com.prateem.jshell.driver;

import com.prateem.jshell.exceptions.JShellException;
import com.prateem.jshell.system.FileSystem;
import com.prateem.jshell.system.utilities.Path;

import java.util.Scanner;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.system.FileSystem
 * @see com.prateem.jshell.system.utilities.Path
 * @see com.prateem.jshell.driver.CommandParser
 */
public class JShell {

  final static String _prompt = "/# ";
  final static String _exit = "exit";

  public static void main(String[] args) {

    FileSystem fileSystem = FileSystem.getFileSystem();
    Path path = new Path();
    CommandParser parser = new CommandParser(path);

    Scanner scanner = new Scanner(System.in);

    showPrompt();
    String input = scanner.nextLine().toString();

    do {

      if (!(input.isEmpty() || input.matches("[ \\t]+"))) {
        try {
          parser.parse(input);
        } catch (JShellException e) {
          System.out.println(e.getMessage());
        }
      }

      showPrompt();
      input = scanner.nextLine().toString();

    } while (!input.toLowerCase().equals(_exit));

  }

  public static void showPrompt() {
    System.out.print(_prompt);
  }

}
