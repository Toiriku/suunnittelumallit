import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> children;
    
    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public void addChild(FileSystemElement element) {
        children.add(element);
    }
    
    public boolean removeChild(FileSystemElement element) {
        return children.remove(element);
    }
    
    public List<FileSystemElement> getChildren() {
        return new ArrayList<>(children);
    }
    
    public boolean isEmpty() {
        return children.isEmpty();
    }
    
    public int getChildCount() {
        return children.size();
    }
    
    @Override
    public String toString() {
        return "Directory{name='" + name + "', children=" + children.size() + "}";
    }
}