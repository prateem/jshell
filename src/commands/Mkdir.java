package commands;

import exceptions.FileSystemException;
import exceptions.ValidationException;
import system.FileSystem;

public class Mkdir extends Command {

  /**
   * Constructor initializes with a file system.
   *
   * @param fileSystem File system to do work on.
   */
  public Mkdir(FileSystem fileSystem) {
    super(fileSystem);
  }

  /**
   * Validate a "mkdir" command.
   *
   * @param command A String to validate as a command.
   * @return Valid "mkdir" command components.
   */
  @Override
  public String[] getValidComponents(String command) throws ValidationException {
    String[] cmd = Command.splitOnWhitespace(command);

    if (!(cmd.length > 1)) {
      Command.throwInvalidCommandError();
    } else {
      String path;

      for (int i = 1; i < cmd.length; i++) {
        path = cmd[i];
        if (!isValidPath(path)) {
          throw new ValidationException(String.format(
              "'mkdir': Invalid path %s", path
          ));
        }
      }
    }

    return cmd;
  }

  /**
   * Run a "mkdir" command.
   *
   * @param validCommand Validated command components to run with.
   */
  @Override
  public void runCommand(String[] validCommand) throws FileSystemException {

  }
}
