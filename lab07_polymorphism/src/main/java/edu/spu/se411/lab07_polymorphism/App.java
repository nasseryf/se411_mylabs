package edu.spu.se411.lab07_polymorphism;

import java.time.LocalDate;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.spu.se411.lab07_polymorphism.exceptions.MissingInformationException;
import edu.spu.se411.lab07_polymorphism.model.Booking;
import edu.spu.se411.lab07_polymorphism.model.CarRentalBooking;
import edu.spu.se411.lab07_polymorphism.model.FlightBooking;
import edu.spu.se411.lab07_polymorphism.model.SeatClass;
import edu.spu.se411.lab07_polymorphism.model.TrainBooking;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");
        System.out.println("Lab 07 - Transportation Bookings");

        try {
            LocalDate travelDate = LocalDate.of(2026, 11, 15);
            FlightBooking flight = new FlightBooking(
                    "F001", "Nasser Alfehaid", travelDate, "Jeddah", 500);
            TrainBooking standardTrain = new TrainBooking(
                    "T001", "Ahmed Ali", travelDate, "Dammam", SeatClass.STANDARD);
            TrainBooking firstClassTrain = new TrainBooking(
                    "T002", "Omar Khalid", travelDate, "Riyadh", SeatClass.FIRST_CLASS);
            CarRentalBooking carRental = new CarRentalBooking(
                    "C001", "Nasser Alfehaid", travelDate, "Riyadh", 150);

            System.out.println("\n1. Missing information examples");
            printBookingPrice(flight);
            printBookingPrice(standardTrain);
            printBookingPrice(carRental);

            System.out.println("\n2. Invalid values (below and above each allowed range)");
            for (double weight : new double[] {-1, 41}) {
                try {
                    flight.setLuggageWeight(weight);
                } catch (InvalidArgumentException exception) {
                    reportException("Luggage weight " + weight, exception);
                }
            }
            for (double distance : new double[] {0, 2001}) {
                try {
                    standardTrain.setDistanceInKilometers(distance);
                } catch (InvalidArgumentException exception) {
                    reportException("Train distance " + distance, exception);
                }
            }
            for (int days : new int[] {0, 31}) {
                try {
                    carRental.setNumberOfRentalDays(days);
                } catch (InvalidArgumentException exception) {
                    reportException("Rental days " + days, exception);
                }
            }

            System.out.println("\n3. Valid bookings using polymorphism");
            flight.setLuggageWeight(10);
            standardTrain.setDistanceInKilometers(300);
            firstClassTrain.setDistanceInKilometers(300);
            carRental.setNumberOfRentalDays(4);

            Booking[] bookings = {flight, standardTrain, firstClassTrain, carRental};
            for (Booking booking : bookings) {
                printBookingPrice(booking);
            }

            System.out.println("\n4. Valid boundary values");
            flight.setLuggageWeight(0);
            printBookingPrice(flight);
            flight.setLuggageWeight(40);
            printBookingPrice(flight);
            standardTrain.setDistanceInKilometers(1);
            printBookingPrice(standardTrain);
            standardTrain.setDistanceInKilometers(2000);
            printBookingPrice(standardTrain);
            carRental.setNumberOfRentalDays(1);
            printBookingPrice(carRental);
            carRental.setNumberOfRentalDays(30);
            printBookingPrice(carRental);
        } catch (InvalidArgumentException exception) {
            reportException("Creating or updating bookings", exception);
        } finally {
            logger.info("Application is stopping...");
            System.out.println("\nFinished. Log file: logs/App/log4j/log.out");
        }
    }

    // The actual object decides which overridden calculation is executed.
    public static double computeTotalPrice(Booking booking)
            throws MissingInformationException, InvalidArgumentException {
        return booking.calculateTotalPrice();
    }

    private static void printBookingPrice(Booking booking) {
        try {
            double totalPrice = computeTotalPrice(booking);
            System.out.printf(Locale.US, "%s | %s | %s | %s | %s | Total: %.2f%n",
                    booking.getBookingId(), booking.getClass().getSimpleName(),
                    booking.getCustomerFullName(), booking.getTravelDate(),
                    booking.getDestinationCity(), totalPrice);
        } catch (MissingInformationException | InvalidArgumentException exception) {
            reportException("Booking " + booking.getBookingId(), exception);
        }
    }

    private static void reportException(String context, Exception exception) {
        System.out.println(context + " -> " + exception.getClass().getSimpleName()
                + ": " + exception.getMessage());
        logger.error(context + ": " + exception.getMessage(), exception);
    }
}
