import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Library {
    private final Map<String, DocumentInterface> documents;
    private final AccessControlService accessControlService;
    
    public Library() {
        documents = new HashMap<>();
        accessControlService = AccessControlService.getInstance();
    }
    
    public void addDocument(String id, LocalDate creationDate, String content) {
        if (documents.containsKey(id)) {
            throw new IllegalArgumentException("Document with ID '" + id + "' already exists");
        }
        
        Document document = new Document(id, creationDate, content);
        documents.put(id, document);
        System.out.println("Added unprotected document: " + id);
    }
    
    public void addProtectedDocument(String id, LocalDate creationDate, String content) {
        if (documents.containsKey(id)) {
            throw new IllegalArgumentException("Document with ID '" + id + "' already exists");
        }
        
        Document realDocument = new Document(id, creationDate, content);
        DocumentProxy proxy = new DocumentProxy(realDocument);
        documents.put(id, proxy);
        System.out.println("Added protected document: " + id);
    }
    
    public void grantAccess(String username, String documentId) {
        if (!documents.containsKey(documentId)) {
            throw new IllegalArgumentException("Document with ID '" + documentId + "' does not exist");
        }
        accessControlService.grantAccess(username, documentId);
    }
    
    public void revokeAccess(String username, String documentId) {
        if (!documents.containsKey(documentId)) {
            throw new IllegalArgumentException("Document with ID '" + documentId + "' does not exist");
        }
        accessControlService.revokeAccess(username, documentId);
    }
    
    public DocumentInterface getDocument(String id) {
        return documents.get(id);
    }
    
    public boolean hasDocument(String id) {
        return documents.containsKey(id);
    }
    
    public int size() {
        return documents.size();
    }
    
    public Set<String> getDocumentIds() {
        return documents.keySet();
    }
    
    public boolean isProtected(String id) {
        DocumentInterface doc = documents.get(id);
        return doc instanceof DocumentProxy;
    }
    
    public boolean removeDocument(String id) {
        DocumentInterface removed = documents.remove(id);
        if (removed != null) {
            System.out.println("Removed document: " + id);
            return true;
        }
        return false;
    }
    
    public void listDocuments() {
        System.out.println("\n=== Library Contents ===");
        if (documents.isEmpty()) {
            System.out.println("No documents in library.");
            return;
        }
        
        for (DocumentInterface doc : documents.values()) {
            String type = (doc instanceof DocumentProxy) ? "Protected" : "Unprotected";
            System.out.println(type + " - ID: " + doc.getId() + 
                             ", Created: " + doc.getCreationDate());
        }
        System.out.println("========================\n");
    }
}