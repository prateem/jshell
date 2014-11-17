package system;

import exceptions.FileSystemException;
import structure.Tree;

/**
 * FileSystem whose root inside of which all manipulations are done.
 */
public class FileSystem {

  /**
   * Singleton instance of FileSystem.
   */
  private static FileSystem fileSystem;

  /**
   * Root of the file system.
   */
  private final Tree root;

  /**
   * Private access level for singleton enforcement.
   */
  private FileSystem() {
    root = new Tree(new Directory("root"));
    root.setParent(root);
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

  /**
   * Return the root of the FileSystem.
   *
   * @return The root node of the FileSystem.
   */
  public Tree<FileSystemObject> getRoot() {
    return root;
  }

  /**
   * Return a new File object. Creates a new File object with the given name
   * and contents and returns it.
   *
   * @param name The name to give to the File.
   * @param contents The contents to set in the File.
   * @return The newly instantiated File.
   */
  public static File makeFile(String name, String contents) {
    return new File(name, contents);
  }

  /**
   * Return a new Folder object. Creates a new Folder object with the given name
   * and empty contents.
   *
   * @param name The name to give to the Folder.
   * @return The newly instantiated Folder.
   */
  public static Directory makeFolder(String name) {
    return new Directory(name);
  }

  public static void throwFileSystemException(String message)
      throws FileSystemException {
    throw new FileSystemException(message);
  }
}
