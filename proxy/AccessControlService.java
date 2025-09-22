import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AccessControlService {
    private static volatile AccessControlService instance;
    private final Set<String> allowedUserDocumentPairs;
    
    private AccessControlService() {
        allowedUserDocumentPairs = ConcurrentHashMap.newKeySet();
    }
    
    public static AccessControlService getInstance() {
        if (instance == null) {
            synchronized (AccessControlService.class) {
                if (instance == null) {
                    instance = new AccessControlService();
                }
            }
        }
        return instance;
    }
    
    public void grantAccess(String username, String documentId) {
        validateParameters(username, documentId);
        String key = createKey(username, documentId);
        allowedUserDocumentPairs.add(key);
        System.out.println("Access granted: " + username + " can access document " + documentId);
    }
    
    public void revokeAccess(String username, String documentId) {
        validateParameters(username, documentId);
        String key = createKey(username, documentId);
        boolean removed = allowedUserDocumentPairs.remove(key);
        if (removed) {
            System.out.println("Access revoked: " + username + " can no longer access document " + documentId);
        }
    }
    
    public boolean isAllowed(String documentId, String username) {
        validateParameters(username, documentId);
        String key = createKey(username, documentId);
        return allowedUserDocumentPairs.contains(key);
    }
    
    public int getPermissionCount() {
        return allowedUserDocumentPairs.size();
    }
    
    public void clearAllPermissions() {
        allowedUserDocumentPairs.clear();
        System.out.println("All access permissions cleared");
    }
    
    private String createKey(String username, String documentId) {
        return username.trim() + ":" + documentId.trim();
    }
    
    private void validateParameters(String username, String documentId) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (documentId == null || documentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Document ID cannot be null or empty");
        }
    }
}