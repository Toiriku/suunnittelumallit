import java.time.LocalDate;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Document Protection System Demo ===\n");
        
        Library library = new Library();
        User alice = new User("alice");
        User bob = new User("bob");
        User charlie = new User("charlie");
        User admin = new User("admin");
        
        System.out.println("Created users: " + alice + ", " + bob + ", " + charlie + ", " + admin + "\n");
        
        library.addDocument("public-doc1", LocalDate.of(2024, 1, 15), 
                           "This is a public document that everyone can read.");
        library.addDocument("readme", LocalDate.of(2024, 2, 1), 
                           "Welcome to our system! This document contains general information.");
        
        library.addProtectedDocument("confidential-report", LocalDate.of(2024, 3, 10), 
                                   "CONFIDENTIAL: This report contains sensitive financial data.");
        library.addProtectedDocument("hr-policies", LocalDate.of(2024, 1, 20), 
                                   "HR Policies: Employee handbook and internal procedures.");
        library.addProtectedDocument("project-alpha", LocalDate.of(2024, 4, 5), 
                                   "Project Alpha specifications and technical details.");
        
        library.grantAccess("alice", "confidential-report");
        library.grantAccess("admin", "confidential-report");
        library.grantAccess("admin", "hr-policies");
        library.grantAccess("alice", "hr-policies");
        library.grantAccess("bob", "project-alpha");
        library.grantAccess("admin", "project-alpha");
        
        library.listDocuments();
        
        demonstrateAccess("public-doc1", alice, library);
        demonstrateAccess("public-doc1", bob, library);
        
        demonstrateAccess("confidential-report", alice, library);
        demonstrateAccess("confidential-report", bob, library);
        demonstrateAccess("confidential-report", admin, library);
        
        demonstrateAccess("hr-policies", alice, library);
        demonstrateAccess("hr-policies", charlie, library);
        
        demonstrateAccess("project-alpha", bob, library);
        demonstrateAccess("project-alpha", alice, library);
        
        System.out.println("\n=== Creation Date Access (Always Public) ===");
        for (String docId : library.getDocumentIds()) {
            DocumentInterface doc = library.getDocument(docId);
            System.out.println("Document '" + docId + "' created on: " + doc.getCreationDate());
        }
        
        System.out.println("\n=== Access Revocation Demo ===");
        System.out.println("Revoking Alice's access to confidential-report...");
        library.revokeAccess("alice", "confidential-report");
        demonstrateAccess("confidential-report", alice, library);
        
        System.out.println("Granting Alice access back to confidential-report...");
        library.grantAccess("alice", "confidential-report");
        demonstrateAccess("confidential-report", alice, library);
        
        System.out.println("\n=== Non-existent Document Access ===");
        DocumentInterface nonExistent = library.getDocument("non-existent");
        if (nonExistent == null) {
            System.out.println("Document 'non-existent' not found in library.");
        }
        
        System.out.println("\n=== Document Type Information ===");
        for (String docId : library.getDocumentIds()) {
            boolean isProtected = library.isProtected(docId);
            System.out.println("Document '" + docId + "' is " + 
                             (isProtected ? "protected" : "unprotected"));
        }
        
        System.out.println("\n=== Demo Complete ===");
    }
    
    private static void demonstrateAccess(String documentId, User user, Library library) {
        System.out.println("\n--- Access Attempt ---");
        System.out.println("User: " + user.getUsername() + " trying to access document: " + documentId);
        
        DocumentInterface document = library.getDocument(documentId);
        if (document == null) {
            System.out.println("ERROR: Document not found!");
            return;
        }
        
        try {
            String content = document.getContent(user);
            System.out.println("SUCCESS: Content retrieved.");
            System.out.println("Content: " + content);
        } catch (AccessDeniedException e) {
            System.out.println("DENIED: " + e.getMessage());
        }
    }
}