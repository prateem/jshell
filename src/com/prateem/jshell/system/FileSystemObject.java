package com.prateem.jshell.system;

import com.prateem.jshell.exceptions.FileSystemException;

/**
 * TODO
 *
 * @author Prateem Shrestha
 * @see com.prateem.jshell.system.FileSystem
 * @see com.prateem.jshell.system.File
 * @see com.prateem.jshell.system.Directory
 */
public abstract class FileSystemObject implements Comparable<FileSystemObject> {

  /**
   * The name of the file system node.
   */
  protected String name;

  /**
   * The parent container of this object.
   */
  private Directory parent;

  /**
   * Constructor which sets the name for the file system node.
   *
   * @param name The name to be set.
   */
  public FileSystemObject(String name) {
    this.name = name;
  }

  /**
   * Ensures a name contain only letters, numbers, the underscore, and the dot
   * character.
   *
   * @param nameToVerify The name to verify.
   * @return True if and only if the name consists of only letters, numbers,
   * the underscore, and the dot.
   */
  public static boolean canBeNamed(String nameToVerify) {
    return nameToVerify.matches("[.]?[\\w]+([.]?[\\w]+)*");
  }

  /**
   * Gets name for file system node.
   *
   * @return Name of the object node.
   */
  public final String getName() {
    return name;
  }

  /**
   * Sets name for file system node.
   *
   * @param name The name to be set.
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Get the parent directory for this system node.
   *
   * @return The parent directory for the system node.
   */
  public final Directory getParent() {
    return parent;
  }

  /**
   * Sets the parent directory for this system node.
   *
   * @param parent The parent directory for this system node.
   */
  protected final void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * Build and return a deep copy of this object.
   *
   * @return A new deep copy of this object.
   * @throws com.prateem.jshell.exceptions.FileSystemException If building of the clone object resulted in
   * an error.
   */
  public abstract FileSystemObject getClone() throws FileSystemException;

  /**
   * String representation of a FileSystemObject should be its name.
   *
   * @return The name of the FileSystemObject.
   */
  @Override
  public String toString() {
    return name;
  }

}
