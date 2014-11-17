package system.path;

import structure.Tree;
import system.FileSystem;
import system.FileSystemObject;
import system.Directory;

import java.util.ArrayList;
import java.util.List;

public class Path {

  private static final FileSystem FILE_SYSTEM = FileSystem.getFileSystem();
  private List<Tree.TreeNode<FileSystemObject>> path;
  private Tree.TreeNode<FileSystemObject> cwd;

  public Path() {
    path = new ArrayList<Tree.TreeNode<FileSystemObject>>();
  }

  public void followPath(String toFollow, Class<?> type) {
    String[] pathComponents = toFollow.split("/");

    for (int i = 0; i < pathComponents.length; i++) {
      String component = pathComponents[i];
      if (component.isEmpty()) {
        setCwd(FILE_SYSTEM.getRoot());
      } else if (component.equals("..")) {
        setCwd(getCwd().getParent());
      } else if (!component.equals(".")) {
        if (type == Directory.class) {
          Tree.TreeNode<FileSystemObject> node = getCwd().getByName(component);
          if (node.getNode() instanceof Directory) {
            setCwd(node);
          }
        }
      }
    }
  }

  public void setCwd(Tree.TreeNode<FileSystemObject> node) {
    cwd = node;
  }

  public Tree.TreeNode<FileSystemObject> getCwd() {
    return cwd;
  }

}
