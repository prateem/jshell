package com.prateemshrestha.jshell.driver;

import com.prateemshrestha.jshell.commands.*;
import com.prateemshrestha.jshell.exceptions.JShellException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.utilities.FileWriter;
import com.prateemshrestha.jshell.system.utilities.OutputCapturer;
import com.prateemshrestha.jshell.system.utilities.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a command input. Discerns between recognized and unrecognized
 * commands. Calls the appropriate command class to validate a command and run
 * it if it is recognized.
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.commands.Command
 * @see com.prateemshrestha.jshell.commands.Cd
 * @see com.prateemshrestha.jshell.commands.Mkdir
 * @see com.prateemshrestha.jshell.commands.Pwd
 */
public class CommandParser {

  /**
   * The path instance to have commands work with.
   */
  private Path path;

  /**
   * The file-writing utility to use if output capturing is enabled.
   */
  private FileWriter writer;

  /**
   * Command name -> Class mapping for all recognized commands.
   */
  private Map<String, Command> commandMap;

  /**
   * Switch to toggle output capture state.
   */
  private boolean capture;

  /**
   * Constructs a parser that will utilize the given shell instance's Path.
   *
   * @param path The Path instance specific to the shell employing this
   *             CommandParser instance.
   */
  public CommandParser(Path path) {
    this.path = path;
    writer = new FileWriter(path);
    populateCommandMap();
  }

  /**
   * Parse the input command string and, if it calls for a recognized command,
   * utilize the appropriate Command class to validate and run the
   * command.
   *
   * @param command The command to parse.
   * @throws JShellException If the command given to parse fails validation or
   * running of the command leads to an error reported by the FileSystem.
   */
  public void parse(String command) throws JShellException {
    // Attempt to get a command name.
    String identifier = Command.stripWhitespace(command)[0];

    if (isValidCommand(identifier)) {
      // Get the appropriate command's class instantiation.
      Command cmd = commandMap.get(identifier);

      // Determine if output redirection has been requested.
      String captureParsed = parseForCapture(command);
      JShellRunner.setCapture(capture);

      // Get verified command components and run the command with them.
      String[] commandComponents = cmd.getValidComponents(captureParsed);
      cmd.run(commandComponents);

      // If output capture was toggled on, write any captured output.
      if (capture) {
        writer.setContents(OutputCapturer.getString()).write();
      }
    } else {
      throw new ValidationException(String.format(
          "%s: command not found", identifier
      ));
    }
  }

  /**
   * Populate the command map with mappings from command name to the
   * appropriate Command class.
   */
  public void populateCommandMap() {
    commandMap = new HashMap<String, Command>();
    commandMap.put("cat", new Cat(path));
    commandMap.put("cd", new Cd(path));
    commandMap.put("cp", new Cp(path));
    commandMap.put("echo", new Echo(path));
    commandMap.put("get", new Get(path));
    commandMap.put("grep", new Grep(path));
    commandMap.put("ls", new Ls(path));
    commandMap.put("man", new Man(path));
    commandMap.put("mkdir", new Mkdir(path));
    commandMap.put("mv", new Mv(path));
    commandMap.put("popd", new PopD(path));
    commandMap.put("pushd", new PushD(path));
    commandMap.put("pwd", new Pwd(path));
    commandMap.put("rm", new Rm(path));
  }

  /**
   * Verify a command name as recognized or unrecognized.
   *
   * @param cmdName The command name to attempt to recognize.
   * @return True if and only if the given command is in the command map,
   * indicating it is a recognized command.
   */
  private boolean isValidCommand(String cmdName) {
    return commandMap.containsKey(cmdName);
  }

  /**
   * Return a command string that has been removed of any output redirection.
   * Parse a command string to determine if output capturing is required, then
   * return the command string for running.
   *
   * @param command The command to be parsed for output redirection.
   * @return The command string devoid of any output redirection entities.
   * @throws ValidationException If a command string with output redirection
   * indicates an invalid path or file name.
   */
  private String parseForCapture(String command)
      throws ValidationException {
    String[] parsedList = parseForStrings(command);
    String parsed = parsedList[0];

    Pattern pattern = Pattern.compile(
            " (?<mode>[>]{1,2})[\\s]+(?<filePath>[^\\s]+)");
    Matcher matcher = pattern.matcher(parsed);

    // Find the first occurrence of redirection in the command string, if any.
    if (matcher.find()) {
      // Redirection to outfile detected. Grab the important elements.
      String filePath = matcher.group("filePath");
      String writeMode = matcher.group("mode");
      writer.validateAndPrepare(filePath, writeMode.equals(">>"));
      OutputCapturer.clear();

      // Initialize capture mode now that the writer has been prepared.
      capture = true;

      // Strip the redirection region from the parsed string.
      parsed = parsed.replace(matcher.group(), "");

      // Replace any placeholders with their original contents.
      for (int i = 1; i < parsedList.length; i++) {
        parsed = parsed.replace(
            String.format("<$JSPP(%d)>", i), parsedList[i]
        );
      }

      return parsed;
    }

    // No redirection to outfile detected.
    capture = false;
    return command;
  }

  /**
   * Return an array of strings with placeholder and placeholder information
   * for any potential string sequence found in a command string. Replaces any
   * portion of the given command String which corresponds to character
   * sequences that are strings (surrounded in double quotes, i.e. ") with a
   * placeholder sequence. Stores any matches replaced by a placeholder in the
   * array.
   *
   * @param command The command String to check and eliminate strings from.
   * @return An array consisting of a parsed command string with placeholders
   * and the contents that originally belonged in each placeholder slot.
   */
  private String[] parseForStrings(String command) {
    Pattern pattern = Pattern.compile("\"[^\"]+\"");
    Matcher matcher = pattern.matcher(command);

    List<String> parsedList = new ArrayList<String>();
    parsedList.add(null);

    int counter = 0;
    while (matcher.find()) {
      // Replaces any matches with a placeholder then adds it to the output.
      String match = matcher.group();
      command = command.replace(
          match, String.format("<$JSPP(%d)>", ++counter)
      );
      parsedList.add(match);
    }

    parsedList.set(0, command);

    return parsedList.toArray(new String[parsedList.size()]);
  }

}
