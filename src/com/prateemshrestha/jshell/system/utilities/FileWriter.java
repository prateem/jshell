package com.prateemshrestha.jshell.system.utilities;

import com.prateemshrestha.jshell.exceptions.FileSystemException;
import com.prateemshrestha.jshell.exceptions.ValidationException;
import com.prateemshrestha.jshell.system.File;
import com.prateemshrestha.jshell.system.FileSystemObject;
import com.prateemshrestha.jshell.system.Directory;

/**
 * Responsible for writing to a File object in the FileSystem. Calls to write()
 * should be preceeded by the the following four setter methods, with only the
 * last two listed being optional: setFileName(), setContents(), setFilePath()
 * and setAppendMode(). All setter methods allow method chaining.
 *
 * The default behaviour is to write to the working directory and to overwrite
 * any potential File that has a name identical to the one specified.
 *
 * @author Prateem Shrestha
 * @see com.prateemshrestha.jshell.system.File
 */
public class FileWriter {

  /**
   * The Path instance specific to the instance of the shell.
   */
  private final Path path;

  /**
   * True if writing is to append to the contents of an existing File object by
   * the same name, if any. False or null (default behaviour) if writing is to
   * overwrite file contents of an existing file by the given name, if any.
   */
  private boolean appendMode;

  /**
   * The valid path to the folder where the written file resides. Defaults to
   * the current working directory (null).
   */
  private String filePath;

  /**
   * The valid name of the file to be written to.
   */
  private String fileName;

  /**
   * The contents to be written.
   */
  private String contents;

  /**
   * Initialize with the Path specific to the instance of the shell, and default
   * behaviour. Set the write behaviour to overwrite rather than append to any
   * potential existing contents, and set the write path to the working
   * directory.
   *
   * @param path The path to utilize for File writing.
   */
  public FileWriter(Path path) {
    this.path = path;
    filePath = null;
    appendMode = false;
  }

  /**
   * Get the file path where file writing is to take place.
   *
   * @return The path string leading to the folder the FileWriter should write
   * to a file in.
   */
  public final String getFilePath() {
    return filePath;
  }

  /**
   * Set the file path where file writing is to take place.
   *
   * @param filePath The path to the folder the FileWriter should write to a
   *                 File in.
   * @return The FileWriter object for method chaining.
   */
  public final FileWriter setFilePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  /**
   * Return whether or not the writer is set to append mode.
   *
   * @return True if the next write operation should attempt to append to an
   * existing file's contents, if any.
   */
  public final boolean getAppendMode() {
    return appendMode;
  }

  /**
   * Set the write mode to append or overwrite.
   *
   * @param appendMode True if the write mode is to be set to append. False if
   *                   the write mode is to be set to overwrite.
   * @return The FileWriter object for method chaining.
   */
  public final FileWriter setAppendMode(boolean appendMode) {
    this.appendMode = appendMode;
    return this;
  }

  /**
   * Get the validated name of the file to be created.
   *
   * @return The name of the file to be created.
   */
  public final String getFileName() {
    return fileName;
  }

  /**
   * Set the name of the file to be created. The name must have been validated
   * as a proper file name.
   *
   * @param fileName The name of the file to be created.
   * @return The FileWriter object for method chaining.
   */
  public final FileWriter setFileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get the contents to be written to the file.
   *
   * @return The contents to be written to the file.
   */
  public final String getContents() {
    return contents;
  }

  /**
   * Set the contents to write to the file.
   *
   * @param contents The contents to write to the file.
   * @return The FileWriter object for method chaining.
   */
  public final FileWriter setContents(String contents) {
    this.contents = contents;
    return this;
  }

  /**
   * Write the specified contents to the appropriate file. Utilize Path to grab
   * the Folder target and write to the existing File by the specified name,
   * overwriting contents or appending to contents as specified. If no File
   * object by the given name exists, create one.
   *
   * @throws FileSystemException If a Folder object at the specified filePath
   * does not exist, or if addition of the File to the Folder object results
   * in an error.
   */
  public final void write() throws FileSystemException {
    Directory location = (Directory) path.get(filePath);
    String contentsToWrite = contents;

    // Only begin the write process if contents are not empty.
    if (!contentsToWrite.isEmpty()) {

      // Does the file already exist?
      if (location.hasFileWithName(fileName)) {
        File existing = (File) location.getChildByName(fileName);

        if (appendMode) {
          // Append to the file's contents.
          existing.appendToContents("\n" + contentsToWrite);
        } else {
          // Overwrite existing contents.
          existing.setContents(contentsToWrite);
        }
      } else {
        // Create and add the file since it does not already exist.
        File newFile = new File(fileName, contentsToWrite);
        location.add(newFile);
      }
    }
  }

  /**
   * Run checks for file path validity and prepare the FileWriter if checks
   * pass. Validate the given file path and file name, setting attributes of the
   * file writer only if validation passes.
   *
   * @param filePath   The file path to validate.
   * @param appendMode True if write mode is to be set to append to contents.
   * @throws ValidationException If the file path given does not lead to a
   * valid directory or if the file name specified is invalid.
   */
  public final void validateAndPrepare(String filePath, boolean appendMode)
      throws ValidationException {
    String[] pathComponents = Path.getPathComponents(filePath);
    String fPath = pathComponents[0];
    String fName = pathComponents[1];

    // Sanity checks. The "validate" part.
    checkForProblems(fPath, fName);

    // Set up file writer.
    this.filePath = fPath;
    this.fileName = fName;
    this.appendMode = appendMode;

    OutputCapturer.clear();
  }

  /**
   * Check for problems in the given path to be written to.
   *
   * @param fPath    The path to the desired location of the file.
   * @param fName    The name of the file to be written to.
   * @throws ValidationException If the desired location is not a Directory, or
   * simply doesn't exist, or if an invalid file name was given.
   */
  private void checkForProblems(String fPath, String fName)
      throws ValidationException {
    String filePath = fPath + "/" + fName;

    if (path.isValid(Directory.class, filePath)) {
      // File path leads to a directory.
      throw new ValidationException(String.format(
          "jshell: cannot create file '%s': Is a directory", filePath));
    } else if (path.isValid(File.class, fPath)) {
      // Location path leads to a file.
      throw new ValidationException(String.format(
          "jshell: '%s': Not a directory", filePath));
    } else if (!path.isValid(Directory.class, fPath)) {
      // Location does not exist.
      throw new ValidationException(String.format(
          "jshell: cannot create file '%s': No such file or directory", filePath
      ));
    } else if (!FileSystemObject.canBeNamed(fName)) {
      // Filename invalid.
      throw new ValidationException(String.format(
          "jshell: cannot create file '%s': invalid file name", filePath));
    }
  }

}
