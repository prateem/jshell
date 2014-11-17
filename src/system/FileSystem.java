package system;

import exceptions.FileSystemException;

/**
 * FileSystem whose root inside of which all manipulations are done.
 *
 * @author Prateem Shrestha
 * @see system.FileSystemObject
 * @see system.File
 * @see system.Directory
 */
public class FileSystem {

  /**
   * Singleton instance of FileSystem.
   */
  private static FileSystem fileSystem;

  /**
   * Root of the file system.
   */
  public static final Directory ROOT = new Directory("root");

  /**
   * Private access level for singleton enforcement.
   */
  private FileSystem() {
    ROOT.setParent(ROOT);
  }

  /**
   * Return the singleton instance of FileSystem. Creates an instance if one
   * is not already available.
   *
   * @return The singleton instance of FileSystem.
   */
  public static FileSystem getFileSystem() {
    if (fileSystem == null) {
      fileSystem = new FileSystem();
    }
    return fileSystem;
  }

}
