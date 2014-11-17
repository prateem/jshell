package driver;

import exceptions.JShellException;
import system.FileSystem;
import system.path.Path;

import java.util.Scanner;

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
