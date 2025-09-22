public class SizeCalculatorVisitor implements FileSystemVisitor {
    private double totalSize = 0.0;
    private int fileCount = 0;
    private int directoryCount = 0;
    
    @Override
    public void visitFile(File file) {
        totalSize += file.getSizeInMB();
        fileCount++;
    }
    
    @Override
    public void visitDirectory(Directory directory) {
        directoryCount++;
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }
    
    public double getTotalSize() {
        return totalSize;
    }
    
    public int getFileCount() {
        return fileCount;
    }
    
    public int getDirectoryCount() {
        return directoryCount;
    }
    
    public void reset() {
        totalSize = 0.0;
        fileCount = 0;
        directoryCount = 0;
    }
    
    public String getSummary() {
        return String.format("Total Size: %.2f MB, Files: %d, Directories: %d", 
                           totalSize, fileCount, directoryCount);
    }
    
    @Override
    public String toString() {
        return getSummary();
    }
}