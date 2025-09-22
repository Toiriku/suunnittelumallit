import java.time.LocalDate;

public class Document implements DocumentInterface {
    private final String id;
    private final LocalDate creationDate;
    private final String content;
    
    public Document(String id, LocalDate creationDate, String content) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Document ID cannot be null or empty");
        }
        if (creationDate == null) {
            throw new IllegalArgumentException("Creation date cannot be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        
        this.id = id.trim();
        this.creationDate = creationDate;
        this.content = content;
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }
    
    @Override
    public String getContent(User user) {
        return content;
    }
    
    String getProtectedContent() {
        return content;
    }
    
    @Override
    public String toString() {
        return "Document{id='" + id + "', creationDate=" + creationDate + 
               ", contentLength=" + content.length() + "}";
    }
}