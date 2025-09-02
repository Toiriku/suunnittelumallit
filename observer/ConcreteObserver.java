package observer;

public class ConcreteObserver implements WeatherObserver {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(int temperature) {
        System.out.println(name + " received update: Current temperature is " + temperature + "°C");
    }
}
