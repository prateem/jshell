package system.utilities;

import system.Directory;
import system.FileSystem;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see system.FileSystem
 * @see system.Directory
 */
public class Path {

  /**
   * The current working directory of this path instance.
   */
  private Directory workingDirectory;

  /**
   * TODO
   */
  public Path() {
    workingDirectory = FileSystem.ROOT;
  }

  /**
   * Get the working directory of this Path instance.
   *
   * @return The working directory Folder object for this Path instance.
   */
  public Directory getWorkingDirectory() {
    return workingDirectory;
  }

}
