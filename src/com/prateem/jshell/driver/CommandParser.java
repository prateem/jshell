package com.prateem.jshell.driver;

import com.prateem.jshell.commands.Cd;
import com.prateem.jshell.commands.Command;
import com.prateem.jshell.commands.Mkdir;
import com.prateem.jshell.commands.PopD;
import com.prateem.jshell.commands.PushD;
import com.prateem.jshell.commands.Pwd;
import com.prateem.jshell.exceptions.JShellException;
import com.prateem.jshell.system.FileSystem;
import com.prateem.jshell.system.utilities.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.commands.Command
 * @see com.prateem.jshell.commands.Cd
 * @see com.prateem.jshell.commands.Mkdir
 * @see com.prateem.jshell.commands.Pwd
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
    commandMap.put("popd", new PopD(path));
    commandMap.put("pushd", new PushD(path));
    commandMap.put("pwd", new Pwd(path));
  }
}
