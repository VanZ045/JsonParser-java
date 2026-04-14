package AST;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectNode extends ASTNode{
    private final Map<String,ASTNode> value = new LinkedHashMap<>();

    public void put(String key, ASTNode node){
        value.put(key,node);
    }


    @Override
    public String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(indent)).append("Object\n");

        for (Map.Entry<String, ASTNode> entry : value.entrySet()) {
            sb.append(indent(indent + 1)).append("Pair\n");
            sb.append(new StringNode(entry.getKey()).toString(indent + 2)).append("\n");
            sb.append(entry.getValue().toString(indent + 2)).append("\n");
        }

        return sb.toString();
    }
}
