package system;

import structure.TreeNode;

public class FileSystem {

  final private Folder home = new Folder("home");
  private TreeNode<FileSystemObject> root;

  public FileSystem() {
    root = new TreeNode<FileSystemObject>(home);
  }

}
