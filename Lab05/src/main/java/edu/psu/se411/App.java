package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;
import edu.psu.se411.exceptions.InsufficientFundsException;

public class App {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        }

        System.out.println("Age valid message.");
    }

    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Age Validation ===");

        try {
            validateAge(20);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            validateAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Exercise 2: Online Wallet ===");

        Wallet wallet = new Wallet(500);
        System.out.println("Initial wallet balance: " + wallet.getBalance());

        try {
            wallet.withdraw(200);
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Wallet balance: " + wallet.getBalance());
        System.out.println("Bank balance: " + wallet.getBankBalance());

        try {
            wallet.withdraw(400);
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Final wallet balance: " + wallet.getBalance());
        System.out.println("Final bank balance: " + wallet.getBankBalance());
    }
}