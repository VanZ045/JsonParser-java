package AST;

public class StringNode extends ASTNode{
    private final String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String toString(int indent) {
        return indent(indent) + "String: " + value;
    }
}
