package observer;

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        WeatherObserver obs1 = new ConcreteObserver("Observer A");
        WeatherObserver obs2 = new ConcreteObserver("Observer B");
        WeatherObserver obs3 = new ConcreteObserver("Observer C");

        station.registerObserver(obs1);
        station.registerObserver(obs2);
        station.registerObserver(obs3);

        station.start();

        try {
            Thread.sleep(15000); // Run for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n>>> Removing Observer B...\n");
        station.removeObserver(obs2);

        try {
            Thread.sleep(15000); // Run for another 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0); // Stop program (since WeatherStation runs forever)
    }
}
