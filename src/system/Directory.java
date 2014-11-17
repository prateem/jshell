package system;

import exceptions.FileSystemException;
import system.utilities.FileSystemObjectComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Directory extends FileSystemObject {

  private static final Comparator<FileSystemObject> COMPARATOR =
      new FileSystemObjectComparator();

  private List<FileSystemObject> children;

  public Directory(String name) {
    super(name);
  }

  public void add(FileSystemObject object) {
    if (object instanceof Directory) {
      addDirectory((Directory) object);
    } else if (object instanceof File) {
      addFile((File) object);
    }

    object.setParent(this);
    sortChildren();
  }

  private void addFile(File file) {
    children.add(file);
  }

  private void addDirectory(Directory directory) {
    if (hasChildWithName(directory.name)) {

    } else {
      children.add(directory);
    }
  }

  public void remove(FileSystemObject object) {
    if (children.contains(object)) {
      children.remove(object);
      object.setParent(null);
    }
  }

  private void sortChildren() {
    Collections.sort(children, COMPARATOR);
  }

  public boolean hasChildWithName(String name) {
    for (FileSystemObject child : children) {
      if (child.name.equals(name)) {
        return true;
      }
    }

    return false;
  }

  public FileSystemObject getChildByName(String name)
      throws FileSystemException {
    for (FileSystemObject child : children) {
      if (child.name.equals(name)) {
        return child;
      }
    }

    throw new FileSystemException("No child by that name exists.");
  }

}
