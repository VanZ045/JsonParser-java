package AST;

public abstract class ASTNode {
    public abstract String toString(int indent);

    protected String indent(int level) {
        return "  ".repeat(level);
    }


}
