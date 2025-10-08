import java.io.IOException;

public class FacadeDemo {
    
    public static void main(String[] args) {
        ApiFacade facade = new ApiFacade();
        
        System.out.println("=== Demo 1: Chuck Norris Joke ===");
        try {
            String joke = facade.getAttributeValueFromJson(
                "https://api.chucknorris.io/jokes/random", 
                "value"
            );
            System.out.println("Joke: " + joke);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Data error: " + e.getMessage());
        }
        
        System.out.println("=== Demo 2: Exchange Rates ===");
        try {
            String date = facade.getAttributeValueFromJson(
                "https://api.fxratesapi.com/latest", 
                "date"
            );
            System.out.println("Date: " + date);
            
            String base = facade.getAttributeValueFromJson(
                "https://api.fxratesapi.com/latest", 
                "base"
            );
            System.out.println("Base currency: " + base);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Data error: " + e.getMessage());
        }
        
        System.out.println("=== Demo 3: Error Handling - Invalid URL ===");
        try {
            facade.getAttributeValueFromJson("not-a-valid-url", "value");
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Data error: " + e.getMessage());
        }
        System.out.println();
        
        System.out.println("=== Demo 4: Error Handling - Missing Attribute ===");
        try {
            facade.getAttributeValueFromJson(
                "https://api.chucknorris.io/jokes/random", 
                "nonExistentAttribute"
            );
        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        }
    }
}