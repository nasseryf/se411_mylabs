package edu.spu.se411.lab07_polymorphism.model;

import java.time.LocalDate;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class FlightBooking extends Booking {

    private final double baseTicketPrice;
    // null means not supplied; zero is a valid luggage weight.
    private Double luggageWeight;

    public FlightBooking(String bookingId, String customerFullName,
                         LocalDate travelDate, String destinationCity,
                         double baseTicketPrice) throws InvalidArgumentException {
        super(bookingId, customerFullName, travelDate, destinationCity);
        validateRange(baseTicketPrice, 0, Double.MAX_VALUE, "Base ticket price");
        this.baseTicketPrice = baseTicketPrice;
    }

    public void setLuggageWeight(double luggageWeight) throws InvalidArgumentException {
        validateRange(luggageWeight, GlobalConfiguration.MIN_LUGGAGE_WEIGHT,
                GlobalConfiguration.MAX_LUGGAGE_WEIGHT, "Luggage weight (kg)");
        this.luggageWeight = luggageWeight;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException {
        if (luggageWeight == null) {
            throw new MissingInformationException("Luggage weight has not been provided.");
        }
        return baseTicketPrice + luggageWeight * GlobalConfiguration.EXTRA_LUGGAGE_RATE;
    }
}
