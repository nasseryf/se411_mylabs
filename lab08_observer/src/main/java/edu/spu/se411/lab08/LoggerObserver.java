package edu.spu.se411.lab08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerObserver implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(LoggerObserver.class);
    private final String name;

    public LoggerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor) {
            Sensor sensor = (Sensor) subject;
            System.out.printf("[%s] %s: %.2f %s%n",
                    name, sensor.getName(), sensor.getReading(), sensor.getUnit());
            logger.info("[{}] {}: {} {}",
                    name, sensor.getName(), sensor.getReading(), sensor.getUnit());
        }
    }
}
