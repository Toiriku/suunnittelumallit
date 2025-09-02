package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation extends Thread {
    private final List<WeatherObserver> observers = new ArrayList<>();
    private int temperature;
    private final int MIN_TEMP = -10;
    private final int MAX_TEMP = 40;
    private final Random random = new Random();

    public WeatherStation() {
        this.temperature = random.nextInt(MAX_TEMP - MIN_TEMP + 1) + MIN_TEMP;
    }

    public synchronized void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    public synchronized void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    private synchronized void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperature);
        }
    }

    private void updateTemperature() {
        int change = random.nextBoolean() ? 1 : -1;
        int newTemp = temperature + change;

        if (newTemp >= MIN_TEMP && newTemp <= MAX_TEMP) {
            temperature = newTemp;
        }
    }

    @Override
    public void run() {
        while (true) {
            updateTemperature();
            notifyObservers();

            try {
                int sleepTime = 1000 * (1 + random.nextInt(5)); // 1-5 seconds
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.println("WeatherStation thread interrupted");
                break;
            }
        }
    }
}
