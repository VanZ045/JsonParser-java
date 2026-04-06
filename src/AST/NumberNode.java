package AST;

public class NumberNode extends ASTNode{
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public String toString(int indent) {
        return indent(indent) + "Number: " + value;
    }
}
