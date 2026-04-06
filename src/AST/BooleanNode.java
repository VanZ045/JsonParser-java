package AST;

public class BooleanNode extends ASTNode{
    private final boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public String toString(int indent) {
        return indent(indent) + value;
    }
}
