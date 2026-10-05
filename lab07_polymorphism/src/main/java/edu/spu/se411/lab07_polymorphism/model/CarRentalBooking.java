package edu.spu.se411.lab07_polymorphism.model;

import java.time.LocalDate;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class CarRentalBooking extends Booking {

    private final double dailyRentalRate;
    private Integer numberOfRentalDays;

    public CarRentalBooking(String bookingId, String customerFullName,
                            LocalDate travelDate, String destinationCity,
                            double dailyRentalRate) throws InvalidArgumentException {
        super(bookingId, customerFullName, travelDate, destinationCity);
        validateRange(dailyRentalRate, 0, Double.MAX_VALUE, "Daily rental rate");
        this.dailyRentalRate = dailyRentalRate;
    }

    public void setNumberOfRentalDays(int numberOfRentalDays)
            throws InvalidArgumentException {
        validateRange(numberOfRentalDays, GlobalConfiguration.MIN_RENTAL_DAYS,
                GlobalConfiguration.MAX_RENTAL_DAYS, "Number of rental days");
        this.numberOfRentalDays = numberOfRentalDays;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException {
        if (numberOfRentalDays == null) {
            throw new MissingInformationException("Number of rental days has not been provided.");
        }
        return dailyRentalRate * numberOfRentalDays;
    }
}
