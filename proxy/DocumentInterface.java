import java.time.LocalDate;

public interface DocumentInterface {
    String getId();
    
    LocalDate getCreationDate();

    String getContent(User user) throws AccessDeniedException;
}