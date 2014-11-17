package system;

import exceptions.FileSystemException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * FileSystemObject that behaves as a directory; contains other
 * FileSystemObjects.
 *
 * @author Prateem Shrestha
 * @see system.File
 */
public class Directory extends FileSystemObject
    implements Iterable<FileSystemObject> {

  /**
   * Comparator for sorting contents.
   */
  private static final Comparator<FileSystemObject> COMPARATOR =
      new FileSystemObjectComparator();

  /**
   * TODO
   *
   * @author Prateem Shrestha
   */
  private static class FileSystemObjectComparator
      implements Comparator<FileSystemObject> {
    /**
     * Compare two FileSystemObjects to each other. If the two objects are of
     * similar extending classes, return the comparison of the object names. If
     * they are of two differing classes, sort Directory objects before File
     * objects.
     *
     * @param fso1 A FileSystemObject to perform comparison with.
     * @param fso2 Another FileSystemObject to perform comparison on.
     * @return -1 if fso1 should be sorted before fso2, or +1 if fso1 should be
     * sorted after fso2. This method should never return 0 since two objects
     * can not share a name in this system.
     */
    @Override
    public int compare(FileSystemObject fso1, FileSystemObject fso2) {
      if (fso1 instanceof Directory && fso2 instanceof File) {
        return -1;
      } else if (fso1 instanceof File && fso2 instanceof Directory) {
        return 1;
      }

      return fso1.name.compareTo(fso2.name);
    }
  }

  /**
   * List of all contents that reside in this Folder.
   */
  private List<FileSystemObject> children;

  /**
   * Initialize with a name.
   *
   * @param name The name for the directory.
   */
  public Directory(String name) {
    super(name);
    children = new ArrayList<FileSystemObject>();
  }

  /**
   * Add a FileSystemObject as a child of this directory.
   *
   * @param object The FileSystemObject to be added.
   * @throws FileSystemException If attempting to add a FileSystemObject
   * resulted in an error.
   */
  public final void add(FileSystemObject object) throws FileSystemException {
    children.add(object);
    object.setParent(this);
    sort();
  }

  /**
   * Remove a FileSystemObject from the contents of this directory.
   *
   * @param object The file system object to be removed.
   */
  public final void remove(FileSystemObject object) {
    if (children.contains(object)) {
      children.remove(object);
      object.setParent(null);
    }
  }

  /**
   * Sort children alphabetically, with Directory objects sorted before File
   * objects.
   */
  private void sort() {
    Collections.sort(children, COMPARATOR);
  }

  /**
   * Check if a Directory object with a specific name is a child of this
   * directory.
   *
   * @param name The folder name to match against.
   * @return True if and only if a Directory by the specified name is a
   * subdirectory of this directory.
   */
  public final boolean hasSubdirectoryWithName(String name) {
    for (FileSystemObject child : children) {
      if (child instanceof Directory && child.name.equals(name)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Check to see if this directory has a File with the given name.
   *
   * @param name The name of the file to search for.
   * @return True if and only if a File by the specified name is a child of this
   * directory.
   */
  public final boolean hasFileWithName(String name) {
    for (FileSystemObject child : children) {
      if (child instanceof File && child.name.equals(name)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Check to see if a child by a specific name already exists in this
   * directory. Does not discriminate on Directory vs. File.
   *
   * @param name The name to search for.
   * @return True if and only if there exists a File or a Directory object by
   * the given name.
   */
  public final boolean hasChildWithName(String name) {
    return hasSubdirectoryWithName(name) || hasFileWithName(name);
  }

  /**
   * Return the FileSystemObject, be it File or Directory, that has the
   * specified name.
   *
   * @param name The name to search for.
   * @return A FileSystemObject with a specific name, if it exists in the
   * contents of this directory.
   * @throws FileSystemException If an object with the requested name does not
   * exist in the contents of this directory.
   */
  public final FileSystemObject getChildByName(String name)
      throws FileSystemException {
    for (FileSystemObject child : children) {
      if (child.name.equals(name)) {
        return child;
      }
    }

    throw new FileSystemException("No child by that name exists.");
  }

  /**
   * Build and return a Directory object that is identical but unique in
   * comparison to this object - a deep copy. Alterations in the original
   * object will not have any effect on the clone object.
   *
   * @return A new Directory object with identical name and contents.
   * @throws FileSystemException If building of the clone object resulted in
   * an error.
   */
  @Override
  public final Directory getClone() throws FileSystemException {
    Directory clone = new Directory(name);

    for (FileSystemObject child : children) {
      clone.add(child.getClone());
    }

    return clone;
  }

  /**
   * Return whether or not this Directory has any children.
   *
   * @return True if and only if the Directory has no FileSystemObjects as
   * children (subdirectories and files).
   */
  public final boolean isEmpty() {
    return children.isEmpty();
  }

  /**
   * Return an iterator that cycles through all FileSystemObjects found in this
   * Directory.
   *
   * @return An iterator object for all FileSystemObjects in this Directory.
   */
  public final Iterator<FileSystemObject> iterator() {
    return children.iterator();
  }

}
