package com.prateemshrestha.jshell.driver;

import com.prateemshrestha.jshell.commands.Cd;
import com.prateemshrestha.jshell.commands.Command;
import com.prateemshrestha.jshell.commands.Mkdir;
import com.prateemshrestha.jshell.commands.PopD;
import com.prateemshrestha.jshell.commands.PushD;
import com.prateemshrestha.jshell.commands.Pwd;
import com.prateemshrestha.jshell.exceptions.JShellException;
import com.prateemshrestha.jshell.system.utilities.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.commands.Command
 * @see com.prateemshrestha.jshell.commands.Cd
 * @see com.prateemshrestha.jshell.commands.Mkdir
 * @see com.prateemshrestha.jshell.commands.Pwd
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
    populateCommandMap();
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
