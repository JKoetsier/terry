package nl.jkoetsier.terry.input.util;

public class IdentifierValidator {

  public static boolean isValidTableIdentifier(String string) {
    return string.matches("^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)?$");
  }

}
