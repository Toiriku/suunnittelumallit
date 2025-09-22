public class File implements FileSystemElement {
    private final String name;
    private final double sizeInMB;
    
    public File(String name, double sizeInMB) {
        this.name = name;
        this.sizeInMB = sizeInMB;
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public double getSizeInMB() {
        return sizeInMB;
    }
    
    public String getExtension() {
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < name.length() - 1) {
            return name.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }
    
    @Override
    public String toString() {
        return "File{name='" + name + "', size=" + sizeInMB + "MB}";
    }
}