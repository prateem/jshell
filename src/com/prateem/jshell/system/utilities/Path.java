package com.prateem.jshell.system.utilities;

import com.prateem.jshell.exceptions.FileSystemException;
import com.prateem.jshell.system.Directory;
import com.prateem.jshell.system.FileSystem;
import com.prateem.jshell.system.FileSystemObject;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Handle Path-related tasks for the unique instance of JShell it is
 * instantiated for.
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.system.FileSystem
 * @see com.prateem.jshell.system.Directory
 */
public class Path {

  /**
   * The current working directory of this path instance.
   */
  private Directory workingDirectory;

  /**
   * A stack to keep track of working directory locations committed to memory.
   */
  private final Deque<String> pathStack;

  /**
   * Initialize by identifying the root of the system as the working directory.
   * Prepare a path stack to store remembered locations in.
   */
  public Path() {
    workingDirectory = FileSystem.ROOT;
    pathStack = new ArrayDeque<String>();
  }

  /**
   * Get the working directory of this Path instance.
   *
   * @return The working directory Folder object for this Path instance.
   */
  public Directory getWorkingDirectory() {
    return workingDirectory;
  }

  /**
   * Split a fully qualified path into the components of location and the name
   * of the object at the end of the path. The first element of the returned
   * array is the path to the Directory that should contain the desired object.
   * The second element of the returned array is simply the name of said object.
   *
   * @param path The path to split.
   * @return A two element array consisting of the fully qualified path string
   * to the location of the object, and the object name.
   */
  public static String[] getPathComponents(String path) {
    String[] pathNodes = path.split("/");
    String[] components = new String[2];
    components[1] = pathNodes[pathNodes.length - 1];

    StringBuilder pathBuilder = new StringBuilder();
    // Maintain absolute paths.
    if (path.startsWith("/")) {
      pathBuilder.append("/");
    }

    for (int i = 0; i < pathNodes.length - 1; i++) {
      pathBuilder.append(pathNodes[i]);

      // Don't terminate with a trailing slash.
      // This is particularly important to prevent infinite looping in get().
      if (i != pathNodes.length - 2) {
        pathBuilder.append("/");
      }
    }

    components[0] = pathBuilder.toString();
    return components;
  }

  /**
   * Return an absolute path to the current working directory.
   *
   * @return An absolute path to the current working directory.
   */
  public final String getAbsolutePath() {
    Directory directory = workingDirectory;
    if (directory == FileSystem.ROOT) {
      return "/";
    }

    // Set up a LIFO list of Directories in the path to this directory
    // starting from this directory, working backwards, until the
    // root directory is reached.
    Deque<Directory> path = new ArrayDeque<Directory>();
    while (directory != FileSystem.ROOT) {
      path.push(directory);
      directory = directory.getParent();
    }

    StringBuilder pathBuilder = new StringBuilder();
    while (!path.isEmpty()) {
      pathBuilder.append("/").append(path.pop());
    }

    return pathBuilder.toString();
  }

  /**
   * Store the path to the current working directory in memory. Adds the current
   * location to the path stack for later recall.
   */
  public final void rememberLocation() {
    pathStack.push(getAbsolutePath());
  }

  /**
   * Return true if there is 1 or more working directory locations in the path
   * stack. Return false otherwise.
   *
   * @return True if and only if path stack has elements in it.
   */
  public final boolean hasStoredLocation() {
    return !pathStack.isEmpty();
  }

  /**
   * Return the most recently stored working directory location.
   */
  public final String getLastStoredLocation() {
    return pathStack.pop();
  }

  /**
   * Update the given path string to include the file system object specified.
   *
   * @param path The path string to update with the addition of the object.
   * @param fso  The object to update into the string.
   * @return The new String path containing the file system object.
   */
  public final String buildPathToObject(String path, FileSystemObject fso) {
    return path + (path.endsWith("/") ? "" : "/") + fso;
  }

  /**
   * Validate a path string. Evaluates whether or not a path string will lead to
   * an existing FileSystemObject.
   *
   * @param type The FileSystemObject type to expect at the end of the path.
   * @param path A path string to be validated.
   * @return True if and only if a FileSystemObject of the specified subclass
   * (Directory or File) is found by the given path string.
   */
  public final boolean isValid(Class<? extends FileSystemObject> type,
                               String path) {
    try {
      FileSystemObject fileSystemObject = get(path);
      if (fileSystemObject.getClass() == type) {
        return true;
      }
    } catch (FileSystemException e) {
      return false;
    }
    return false;
  }

  /**
   * Get a FileSystemObject by a valid fully qualified path string.
   *
   * @param path A valid fully qualified path to a FileSystemObject.
   * @return The FileSystemObject found at the given path.
   * @throws FileSystemException If getting the
   */
  public final FileSystemObject get(String path) throws FileSystemException {
    if (path.equals("/") || path.equals("~")) {
      return FileSystem.ROOT;
    }

    Directory location = workingDirectory;

    if (path.equals(".")) {
      return location;
    } else if (path.equals("..")) {
      return location.getParent();
    }

    // Find the Directory of the desired object.
    if (path.contains("/")) {
      String[] pathComponents = getPathComponents(path);
      location = (Directory) get(pathComponents[0]);
      path = pathComponents[1];
    }

    return location.getChildByName(path);
  }

  /**
   * Follow a specific path and update the working directory.
   *
   * @param path A verified path string to follow.
   * @throws FileSystemException If attempting to follow the path resulted
   * in an error, most likely from trying to change the working directory.
   */
  public final void goTo(String path) throws FileSystemException {
    workingDirectory = (Directory) get(path);
  }

}
