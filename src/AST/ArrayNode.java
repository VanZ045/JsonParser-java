package AST;
import java.util.ArrayList;
import java.util.List;

public class ArrayNode extends ASTNode {
    private final List<ASTNode> value;

    public ArrayNode() {
        this.value = new ArrayList<>();
    }

    public void add(ASTNode node){
        value.add(node);
    }

    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(indent)).append("Array\n");

        for (int i = 0; i < value.size(); i++) {
            if (i==value.size()-1){
                sb.append(value.get(i).toString(indent + 1));
                break;
            }
            sb.append(value.get(i).toString(indent + 1)).append("\n");

        }
        return sb.toString();
    }
}
