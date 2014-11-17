package system.utilities;

import system.Directory;
import system.File;
import system.FileSystemObject;

import java.util.Comparator;

public class FileSystemObjectComparator
    implements Comparator<FileSystemObject> {

  /**
   * TODO
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the
   * first argument is less than, equal to, or greater than the
   * second.
   * @throws NullPointerException if an argument is null and this
   * comparator does not permit null arguments
   * @throws ClassCastException if the arguments' types prevent them from
   * being compared by this comparator.
   */
  @Override
  public int compare(FileSystemObject o1, FileSystemObject o2) {
    if (o1 instanceof Directory && o2 instanceof File) {
      return -1;
    } else if (o1 instanceof File && o2 instanceof Directory) {
      return 1;
    }

    return o1.getName().compareTo(o2.getName());
  }
}
