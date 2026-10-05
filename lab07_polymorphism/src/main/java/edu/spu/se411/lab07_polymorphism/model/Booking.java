package edu.spu.se411.lab07_polymorphism.model;

import java.time.LocalDate;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public abstract class Booking {

    private final String bookingId;
    private final String customerFullName;
    private final LocalDate travelDate;
    private final String destinationCity;

    protected Booking(String bookingId, String customerFullName,
                      LocalDate travelDate, String destinationCity) {
        this.bookingId = bookingId;
        this.customerFullName = customerFullName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    // All booking types reuse the same numeric range validation.
    protected static void validateRange(double value, double minimum,
                                        double maximum, String fieldName)
            throws InvalidArgumentException {
        if (!Double.isFinite(value) || value < minimum || value > maximum) {
            throw new InvalidArgumentException(
                    fieldName + " must be between " + minimum + " and " + maximum + ".");
        }
    }

    public abstract double calculateTotalPrice()
            throws MissingInformationException, InvalidArgumentException;
}
