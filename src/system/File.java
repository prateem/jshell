package system;

public class File extends FileSystemObject {

  private String contents;

  public File(String name) {
    super(name);
  }

  public File(String name, String contents) {
    this(name);
    setContents(contents);
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

}
