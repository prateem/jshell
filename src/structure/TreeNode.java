package structure;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
  
  private TreeNode<T> parent;
  private List<TreeNode<T>> children;
  private T nodeContent;
  
  public TreeNode(T nodeContent) {
    this.nodeContent = nodeContent;
    this.children = new ArrayList<TreeNode<T>>();
  }
  
  private void addChild(TreeNode<T> child) {
    children.add(child);
    child.setParent(this);
  }
  
  public void addChild(T child) {
    TreeNode<T> newNode = new TreeNode<T>(child);
    addChild(newNode);
  }
  
  private void setParent(TreeNode<T> node) {
    this.parent = node;
  }
  
  public TreeNode<T> getParent() {
    return parent;
  }
  
  public TreeNode<T> getChild(int index) {
    return children.get(index);
  }
  
  public TreeNode<T> getChild(int index, TreeNode<T> parent) {
    return parent.getChild(index);
  }
  
  public TreeNode<T> getChild(T obj) throws Exception {
    for (TreeNode<T> child : children) {
      if (child.getNodeContent() == obj) {
        return child;
      }
    }
    throw new Exception("Node not found.");
  }
  
  public List<TreeNode<T>> getChildren() {
    return children;
  }
  
  public T getNodeContent() {
    return nodeContent;
  }
  
  public TreeNode<T> removeChild(T obj) throws Exception {
    TreeNode<T> child = getChild(obj);
    children.remove(child);
    child.setParent(null);
    return child;
  }
  
  public void moveChildToNode(T obj, TreeNode<T> newParent) throws Exception {
    TreeNode<T> child = removeChild(obj);
    newParent.addChild(child);
  }
  
}

