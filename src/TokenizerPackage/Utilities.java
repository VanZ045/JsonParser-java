package TokenizerPackage;

public class Utilities {

    static boolean isNull(StringBuilder value) {
        return value.toString().equals("null");
    }

    static boolean isBooleanFalse(StringBuilder value) {
        return value.toString().equals("false");
    }

    static boolean isBooleanTrue(StringBuilder value) {
        return value.toString().equals("true");
    }

    static boolean isNumber(char c) {
        return Character.isDigit(c) || c == '.' || c == '-' || c == 'e' || c == 'E' || c == '+';
    }

}
