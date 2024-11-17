import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Directory extends Node{
    private Map<String, Node> children;

    public Directory(String name){
        super(name);
        children = new HashMap<>();
    }

    public Node getChild(String name){
        return children.get(name);
    }

    public void addChild(Node child){
        children.put(child.getName(), child);
    }

    public void removeChild(String name){
        children.remove(name);
    }

    public Collection<Node> getChildren(){
        return children.values();
    }

    @Override
    public boolean isFile(){
        return false;
    }
}
