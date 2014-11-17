package driver;

import exceptions.JShellException;
import system.FileSystem;
import system.path.Path;

public class CommandParser {

  private static final FileSystem FILE_SYSTEM = FileSystem.getFileSystem();
  private Path path;

  public CommandParser(Path path) {
    this.path = path;
  }

  public void parse(String command) throws JShellException {

  }
}
