import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor implements FileSystemVisitor {
    private final List<File> matchingFiles;
    private final Predicate<File> searchCriteria;
    private final String searchDescription;
    
    public SearchVisitor(Predicate<File> searchCriteria, String searchDescription) {
        this.searchCriteria = searchCriteria;
        this.searchDescription = searchDescription;
        this.matchingFiles = new ArrayList<>();
    }
    
    public static SearchVisitor byExtension(String extension) {
        return new SearchVisitor(
            file -> file.getExtension().equalsIgnoreCase(extension),
            "files with extension ." + extension
        );
    }
    
    public static SearchVisitor byNamePattern(String pattern) {
        return new SearchVisitor(
            file -> file.getName().toLowerCase().contains(pattern.toLowerCase()),
            "files containing '" + pattern + "'"
        );
    }
    
    public static SearchVisitor byMinSize(double sizeInMB) {
        return new SearchVisitor(
            file -> file.getSizeInMB() > sizeInMB,
            "files larger than " + sizeInMB + "MB"
        );
    }
    
    public static SearchVisitor byMaxSize(double sizeInMB) {
        return new SearchVisitor(
            file -> file.getSizeInMB() < sizeInMB,
            "files smaller than " + sizeInMB + "MB"
        );
    }
    
    @Override
    public void visitFile(File file) {
        if (searchCriteria.test(file)) {
            matchingFiles.add(file);
        }
    }
    
    @Override
    public void visitDirectory(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }
    
    public List<File> getMatchingFiles() {
        return new ArrayList<>(matchingFiles);
    }
    
    public int getMatchCount() {
        return matchingFiles.size();
    }
    
    public String getSearchDescription() {
        return searchDescription;
    }
    
    public void reset() {
        matchingFiles.clear();
    }
    
    public String getSummary() {
        return String.format("Found %d %s", matchingFiles.size(), searchDescription);
    }
    
    @Override
    public String toString() {
        return getSummary();
    }
}