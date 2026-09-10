package com.codsoft.banking.model;

import com.codsoft.banking.exception.InsufficientBalanceException;
import com.codsoft.banking.exception.InvalidAmountException;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private final int accountNumber;
    private final String holderName;
    protected double balance;

    private final List<Transaction> transactions;

    public Account(int accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();

        if (initialBalance > 0) {
            transactions.add(
                    new Transaction("Initial Deposit", initialBalance, balance)
            );
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance += amount;

        transactions.add(
                new Transaction("Deposit", amount, balance)
        );
    }

    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Available balance: ₹" + balance
            );
        }

        balance -= amount;

        transactions.add(
                new Transaction("Withdrawal", amount, balance)
        );
    }

    public void transferTo(Account receiver, double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (receiver == null) {
            throw new IllegalArgumentException("Receiver account not found.");
        }

        withdraw(amount);

        receiver.balance += amount;

        receiver.transactions.add(
                new Transaction(
                        "Transfer Received",
                        amount,
                        receiver.balance
                )
        );

        transactions.add(
                new Transaction(
                        "Transfer Sent",
                        amount,
                        balance
                )
        );
    }

    public void displayTransactions() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public abstract String getAccountType();

    public void displayAccountDetails() {

        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println("Account Type : " + getAccountType());
        System.out.println("Account No   : " + accountNumber);
        System.out.println("Holder Name  : " + holderName);
        System.out.printf("Balance      : ₹%.2f%n", balance);
    }
}