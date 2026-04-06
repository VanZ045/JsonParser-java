package TokenizerPackage;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer extends Utilities{

    public static List<Token> tokenize(String input){
        int iterator = 0;
        List<Token> tokens = new ArrayList<>();

        while (iterator < input.length()){
            char currentChar = input.charAt(iterator);

            if (currentChar=='{'){
                tokens.add(new Token(TokenType.BraceOpen,"{"));
                iterator++;
                continue;
            }

            if (currentChar=='}'){
                tokens.add(new Token(TokenType.BraceClose,"}"));
                iterator++;
                continue;
            }

            if (currentChar=='['){
                tokens.add(new Token(TokenType.BracketOpen,"["));
                iterator++;
                continue;
            }

            if (currentChar==']'){
                tokens.add(new Token(TokenType.BracketClose,"]"));
                iterator++;
                continue;
            }

            if (currentChar==':'){
                tokens.add(new Token(TokenType.Colon,":"));
                iterator++;
                continue;
            }

            if (currentChar==','){
                tokens.add(new Token(TokenType.Comma,","));
                iterator++;
                continue;
            }


            if (currentChar == '"') {
                iterator++; // consume starting "
                StringBuilder value = new StringBuilder();

                while (iterator < input.length()) {
                    char c = input.charAt(iterator);

                    if (c == '\\') { // escape sequence
                        iterator++; // skip backslash
                        if (iterator >= input.length()) break;

                        char escaped = input.charAt(iterator);
                        switch (escaped) {
                            case '"': value.append('"'); break;
                            case '\\': value.append('\\'); break;
                            case '/': value.append('/'); break;
                            case 'b': value.append('\b'); break;
                            default: value.append(escaped); // unknown escape, keep literal
                        }
                    } else if (c == '"') {
                        iterator++; // closing "
                        break;
                    } else {
                        value.append(c);
                    }

                    iterator++;
                }

                tokens.add(new Token(TokenType.String, value.toString()));
                continue;
            }

            if (Character.isDigit(currentChar) || currentChar == '-') {

                StringBuilder value = new StringBuilder();

                while (iterator < input.length()) {

                    char c = input.charAt(iterator);

                    if (isNumber(c)) {
                        value.append(c);
                        iterator++;
                    } else {
                        break;
                    }
                }

                tokens.add(new Token(TokenType.Number, value.toString()));
                continue;
            }

            if (Character.isLetter(currentChar)){
                StringBuilder value = new StringBuilder();

                while (iterator<input.length() && Character.isLetter(input.charAt(iterator))){
                    value.append(input.charAt(iterator));
                    iterator++;
                }

                if (isBooleanTrue(value)) tokens.add((new Token(TokenType.True,value.toString())));
                else if (isBooleanFalse(value)) tokens.add((new Token(TokenType.False,value.toString())));
                else if (isNull(value)) tokens.add((new Token(TokenType.Null,value.toString())));
                else throw new Error("Unexpected value: " + value);
                continue;
            }

            if (Character.isWhitespace(currentChar)){
                iterator++;
                continue;
            }

            throw new Error("Unexpected character: " + currentChar);

        }

        return tokens;
    }

    public static void printTokens(List<Token> tokens){
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }


}
