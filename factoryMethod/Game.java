package factoryMethod;

import java.util.Random;

public class Game {
    private static Random random = new Random();

    public static Map createMap(int width, int height) {
        if (random.nextBoolean()) {
            System.out.println("Generating City");
            return new CityMap(width, height);
        } else {
            System.out.println("Generating Wilderness");
            return new WildernessMap(width, height);
        }
    }

    public static void main(String[] args) {
        Map map = createMap(10, 10);
        map.display();
    }
}
