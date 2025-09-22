import java.time.LocalDate;

public class DocumentProxy implements DocumentInterface {
    private final Document realDocument;
    private final AccessControlService accessControlService;
    
    public DocumentProxy(Document realDocument) {
        if (realDocument == null) {
            throw new IllegalArgumentException("Real document cannot be null");
        }
        this.realDocument = realDocument;
        this.accessControlService = AccessControlService.getInstance();
    }
    
    @Override
    public String getId() {
        return realDocument.getId();
    }
    
    @Override
    public LocalDate getCreationDate() {
        return realDocument.getCreationDate();
    }
    
    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (user == null) {
            throw new AccessDeniedException("User cannot be null");
        }
        
        if (accessControlService.isAllowed(realDocument.getId(), user.getUsername())) {
            return realDocument.getProtectedContent();
        } else {
            throw new AccessDeniedException(
                "Access denied for user '" + user.getUsername() + 
                "' to document '" + realDocument.getId() + "'"
            );
        }
    }
    
    Document getRealDocument() {
        return realDocument;
    }
    
    @Override
    public String toString() {
        return "DocumentProxy{documentId='" + realDocument.getId() + 
               "', creationDate=" + realDocument.getCreationDate() + "}";
    }
}