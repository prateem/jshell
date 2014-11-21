package system.utilities;

/**
 * Captures strings given to it in a StringBuilder buffer and allows the
 * retrieval of the resultant string.
 *
 * @author Prateem Shrestha
 */
public class OutputCapturer {

  /**
   * Initializes a StringBuilder to buffer given strings into.
   */
  private static final StringBuilder BUILDER = new StringBuilder();

  /**
   * Captures a string and adds it to the buffered output.
   *
   * @param text The string to add to the output buffer.
   */
  public static void capture(String text) {
    BUILDER.append(String.format("%s%s",
        BUILDER.length() == 0 ? "" : "\n", text));
  }

  /**
   * Return the buffered string.
   *
   * @return The output as a single String entity.
   */
  public static String getString() {
    return BUILDER.toString();
  }

  /**
   * Clear the contents of the output buffer.
   */
  public static void clear() {
    // Set the length of the output buffer to 0 (i.e. no characters).
    BUILDER.setLength(0);
  }

}
