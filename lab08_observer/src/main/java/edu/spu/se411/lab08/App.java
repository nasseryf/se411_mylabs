package edu.spu.se411.lab08;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        logger.info("Application is starting...");

        Sensor temp = new TemperatureSensor();
        Sensor humidity = new HumiditySensor();

        Observer dashboard = new DashboardObserver("Dashboard");
        Observer fileLogger = new LoggerObserver("Logger");

        temp.register(dashboard);
        temp.register(fileLogger);
        humidity.register(dashboard);
        humidity.register(fileLogger);

        Random random = new Random();
        int i = 0;

        System.out.println("1. Sensor readings");
        while (i < 10) {
            System.out.printf("Reading %d%n", i + 1);
            temp.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);
            i++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Simulation interrupted.", e);
                return;
            }
        }

        System.out.println("\n2. Unregister the dashboard from temperature");
        System.out.println("Only Logger should receive this temperature update:");
        temp.unregister(dashboard);
        temp.setReading(36);
        temp.register(dashboard);

        System.out.println("\n3. Clone the temperature sensor");
        Sensor clonedTemp = temp.clone();
        System.out.printf("Original: %.2f C | Clone: %.2f C%n",
                temp.getReading(), clonedTemp.getReading());

        System.out.println("Update the clone before registration: no observer output expected.");
        clonedTemp.setReading(22);
        System.out.printf("Original: %.2f C | Clone: %.2f C%n",
                temp.getReading(), clonedTemp.getReading());

        Observer cloneDashboard = new DashboardObserver("Clone Dashboard");
        Observer cloneLogger = new LoggerObserver("Clone Logger");
        clonedTemp.register(cloneDashboard);
        clonedTemp.register(cloneLogger);

        System.out.println("\nUpdate the clone: only Clone Dashboard and Clone Logger:");
        clonedTemp.setReading(24);

        System.out.println("\nUpdate the original: only Dashboard and Logger:");
        temp.setReading(30);
        System.out.printf("Original: %.2f C | Clone: %.2f C%n",
                temp.getReading(), clonedTemp.getReading());

        System.out.println("\n4. Keep the same reading: no observer output expected.");
        temp.setReading(30);

        logger.info("Application finished.");
        System.out.println("\nApplication finished.");
    }
}
