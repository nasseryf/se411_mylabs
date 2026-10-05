package edu.spu.se411.lab08;

import java.util.ArrayList;
import java.util.List;

public abstract class Sensor implements Subject, Cloneable {
    private final String name;
    private final String unit;
    private double reading;
    private List<Observer> observers;

    public Sensor(String name, String unit) {
        this.name = name;
        this.unit = unit;
        observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        if (Double.compare(this.reading, reading) != 0) {
            this.reading = reading;
            notifyObservers();
        }
    }

    @Override
    public void register(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public Sensor clone() throws CloneNotSupportedException {
        Sensor copy = (Sensor) super.clone();
        // The clone starts with its own empty observer list.
        copy.observers = new ArrayList<>();
        return copy;
    }
}
