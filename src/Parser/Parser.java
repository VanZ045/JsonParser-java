package Parser;
import AST.*;
import TokenizerPackage.Token;
import TokenizerPackage.TokenType;
import TokenizerPackage.Tokenizer;

import java.util.List;
import java.util.Scanner;

public class Parser {

    private final List<Token> tokens;
    private int iterator = 0;

    public Parser(List<Token> tokens){
        if (tokens == null || tokens.isEmpty()){
            throw new RuntimeException("Nothing to parse. Exiting!");
        }
        this.tokens=tokens;
    }

    private Token advance(){
        iterator++;
        return tokens.get(iterator);
    }

    public ASTNode parse(){
        return  parseValue();
    }

    public ASTNode parseValue(){
        Token token = tokens.get(iterator);

        switch (token.getType()){
            case String:
                return new StringNode(token.getValue());
            case Number:
                return new NumberNode(Double.parseDouble(token.getValue()));
            case True:
                return new BooleanNode(true);
            case False:
                return new BooleanNode(false);
            case Null:
                return new NullNode();
            case BraceOpen:
                return parseObject();
            case BracketOpen:
                return parseArray();
            default:
                throw new RuntimeException("Unexpected token type: " + token.getType());
        }
    }

    private ASTNode parseObject(){
        ObjectNode node = new ObjectNode();
        Token token = advance(); //consume { and return next token (has to be string)

        while (token.getType()!= TokenType.BraceClose){
            if (token.getType()==TokenType.String){
                String key = token.getValue();
                token=advance();//consume key and advance to colon

                if (token.getType()!=TokenType.Colon){throw new Error("Expected a colon for valid json object! Token type: "+token.getType());}

                token=advance();//consume colon and get the value of the key-value pair

                ASTNode value = parseValue(); //recursively call parseValue() to handle json objects for value
                node.put(key,value);
            }else {
                throw new Error("Expected a string key in object. Token type: " + token.getType());
            }
            token = advance();
            if (token.getType()==TokenType.Comma){token=advance();}
        }
        return  node;
    }

    private ASTNode parseArray(){
        ArrayNode node = new ArrayNode();
        Token token = advance(); // eat [ and get value

        while (token.getType()!=TokenType.BracketClose){
            ASTNode value = parseValue();
            node.add(value);
            token = advance();
            if (token.getType()==TokenType.Comma){token=advance();}
        }
        return node;
    }

    public static StringBuilder readInput() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder jsonInput = new StringBuilder();

        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            jsonInput.append(line).append("\n");
        }

        scanner.close();
        return jsonInput;
    }

//    System.out.println("JSON read:");
//        System.out.println(input);
    public static void printTree(String input) {
        List<Token> tokens = Tokenizer.tokenize(input);
        Tokenizer.printTokens(tokens);

        Parser parser = new Parser(tokens);

        System.out.println("Pretty print of json object!");
        System.out.println("============================");
        System.out.println(parser.parse().toString(0));
    }
}
