import java.io.IOException;

public class JokeClientWithFacade {
    private ApiFacade facade = new ApiFacade();
    
    public String getRandomJoke() throws IOException, IllegalArgumentException {
        return facade.getAttributeValueFromJson(
            "https://api.chucknorris.io/jokes/random", 
            "value"
        );
    }
    
    public static void main(String[] args) {
        JokeClientWithFacade client = new JokeClientWithFacade();
        
        System.out.println("=== Chuck Norris Joke ===");
        try {
            String joke = client.getRandomJoke();
            System.out.println(joke);
        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Data error: " + e.getMessage());
        }
    }
}