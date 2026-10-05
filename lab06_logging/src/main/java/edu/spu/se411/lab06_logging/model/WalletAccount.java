package edu.spu.se411.lab06_logging.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;

public class WalletAccount {

    private static final Logger logger =
            LoggerFactory.getLogger(WalletAccount.class);

    private double balance;

    public WalletAccount(double balance) {
        setBalance(balance);
        logger.debug("Wallet account created. Balance: {}", balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        logger.debug("Withdrawal requested. Amount: {}, balance: {}",
                amount, balance);

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Cannot withdraw negative number: " + amount);
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Your balance is " + balance);
        }

        balance -= amount;

        logger.debug("Withdrawal successful. Amount: {}, remaining balance: {}",
                amount, balance);
    }

    public void deposit(double amount) throws IllegalArgumentException {
        logger.debug("Deposit requested. Amount: {}, balance: {}",
                amount, balance);

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Cannot deposit negative number: " + amount);
        }

        balance += amount;

        logger.debug("Deposit successful. Amount: {}, new balance: {}",
                amount, balance);
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Balance cannot be negative: " + balance);
        }

        this.balance = balance;
    }
}