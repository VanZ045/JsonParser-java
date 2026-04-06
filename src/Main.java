import static Parser.Parser.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder jsonInput = readInput();
        String input = jsonInput.toString();
        printTree(input);
    }
}


