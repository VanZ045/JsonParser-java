package AST;

public class NullNode extends ASTNode{
    @Override
    public String toString(int indent) {
        return indent(indent)+"null";
    }
}
