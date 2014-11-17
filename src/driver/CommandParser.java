package driver;

import commands.Cd;
import commands.Command;
import commands.Mkdir;
import commands.Pwd;
import exceptions.JShellException;
import system.FileSystem;
import system.utilities.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see commands.Command
 * @see commands.Cd
 * @see commands.Mkdir
 * @see commands.Pwd
 */
public class CommandParser {

  /**
   * TODO
   */
  private Path path;

  /**
   * TODO
   */
  private Map<String, Command> commandMap;

  /**
   * TODO
   *
   * @param path TODO
   */
  public CommandParser(Path path) {
    this.path = path;
  }

  /**
   * TODO
   *
   * @param command TODO
   * @throws JShellException TODO
   */
  public void parse(String command) throws JShellException {

  }

  /**
   * TODO
   */
  public void populateCommandMap() {
    commandMap = new HashMap<String, Command>();
    commandMap.put("cd", new Cd(path));
    commandMap.put("mkdir", new Mkdir(path));
    commandMap.put("pwd", new Pwd(path));
  }
}
