package edu.spu.se411.lab07_polymorphism.model;

import java.time.LocalDate;

import edu.spu.se411.lab07_polymorphism.config.GlobalConfiguration;
import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class TrainBooking extends Booking {

    private final SeatClass seatClass;
    private Double distanceInKilometers;

    public TrainBooking(String bookingId, String customerFullName,
                        LocalDate travelDate, String destinationCity,
                        SeatClass seatClass) throws InvalidArgumentException {
        super(bookingId, customerFullName, travelDate, destinationCity);
        if (seatClass == null) {
            throw new InvalidArgumentException("Seat class must be STANDARD or FIRST_CLASS.");
        }
        this.seatClass = seatClass;
    }

    public void setDistanceInKilometers(double distanceInKilometers)
            throws InvalidArgumentException {
        validateRange(distanceInKilometers, GlobalConfiguration.MIN_TRAIN_DISTANCE,
                GlobalConfiguration.MAX_TRAIN_DISTANCE, "Train distance (km)");
        this.distanceInKilometers = distanceInKilometers;
    }

    @Override
    public double calculateTotalPrice() throws MissingInformationException {
        if (distanceInKilometers == null) {
            throw new MissingInformationException("Train distance has not been provided.");
        }
        double rate = seatClass == SeatClass.STANDARD
                ? GlobalConfiguration.TRAIN_STANDARD_RATE
                : GlobalConfiguration.TRAIN_FIRST_CLASS_RATE;
        return distanceInKilometers * rate;
    }
}
