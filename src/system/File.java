package system;

/**
 * Basic node of a FileSystem. Has a name and contents.
 *
 * @author Prateem Shrestha
 * @see system.Directory
 */
public class File extends FileSystemObject {

  /**
   * File contents for this object.
   */
  private String contents;

  /**
   * Initializes object with only a name.
   *
   * @param name Name for the file object.
   */
  public File(String name) {
    super(name);
  }

  /**
   * Initializes object with name and contents of the file.
   *
   * @param name     The name for the file.
   * @param contents The contents for the file.
   */
  public File(String name, String contents) {
    this(name);
    this.contents = contents;
  }

  /**
   * Gets the contents for the file.
   *
   * @return The contents of the file.
   */
  public final String getContents() {
    return contents;
  }

  /**
   * Sets the contents for the file.
   *
   * @param contents The contents to be set.
   */
  public final void setContents(String contents) {
    this.contents = contents;
  }

  /**
   * Appends to the current contents of the file.
   *
   * @param newContent New string to be appended.
   */
  public final void appendToContents(String newContent) {
    contents = contents + newContent;
  }

  /**
   * Returns a File object that is identical but unique in comparison to
   * this object - a deep copy. Alterations in the original object will not
   * have any effect on the clone object.
   *
   * @return A new File object with identical name and contents.
   */
  @Override
  public File getClone() {
    return new File(name, contents);
  }

}
