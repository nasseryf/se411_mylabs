package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class Wallet {

    private double balance;
    private double bankBalance;

    public Wallet(double balance) {
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException("Invalid starting balance.");
        }

        this.balance = balance;
        this.bankBalance = 0;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal amount must be positive and finite."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient funds. Available balance: " + balance
            );
        }

        balance -= amount;
        bankBalance += amount;

        System.out.println("Transferred to bank: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public double getBankBalance() {
        return bankBalance;
    }
}