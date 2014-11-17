package system;

public abstract class FileSystemObject {

  protected String name;

  private Directory parent;

  public FileSystemObject(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Directory getParent() {
    return parent;
  }

  protected void setParent(Directory parent) {
    this.parent = parent;
  }

  @Override
  public String toString() {
    return name;
  }

}
