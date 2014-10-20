package system;

public abstract class FileSystemObject {

  private String name;

  public FileSystemObject(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getName();
  }

}
