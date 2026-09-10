package com.codsoft.banking.service;

import com.codsoft.banking.exception.AccountNotFoundException;
import com.codsoft.banking.exception.InsufficientBalanceException;
import com.codsoft.banking.exception.InvalidAmountException;
import com.codsoft.banking.model.*;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<Integer, Account> accounts;

    private int nextAccountNumber = 1001;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Account createAccount(
            String name,
            String accountType,
            double initialDeposit)
            throws InvalidAmountException {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Account holder name cannot be empty."
            );
        }

        if (initialDeposit < 0) {
            throw new InvalidAmountException(
                    "Initial deposit cannot be negative."
            );
        }

        if (accountType.equalsIgnoreCase("savings")) {

            if (initialDeposit < SavingsAccount.getMinimumBalance()) {
                throw new InvalidAmountException(
                        "Savings account requires minimum balance of ₹"
                                + SavingsAccount.getMinimumBalance()
                );
            }

            Account account = new SavingsAccount(
                    nextAccountNumber,
                    name,
                    initialDeposit
            );

            accounts.put(nextAccountNumber, account);
            nextAccountNumber++;

            return account;

        } else if (accountType.equalsIgnoreCase("current")) {

            Account account = new CurrentAccount(
                    nextAccountNumber,
                    name,
                    initialDeposit
            );

            accounts.put(nextAccountNumber, account);
            nextAccountNumber++;

            return account;

        } else {

            throw new IllegalArgumentException(
                    "Invalid account type."
            );
        }
    }

    public Account findAccount(int accountNumber)
            throws AccountNotFoundException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account " + accountNumber + " does not exist."
            );
        }

        return account;
    }

    public void deposit(int accountNumber, double amount)
            throws AccountNotFoundException, InvalidAmountException {

        Account account = findAccount(accountNumber);

        account.deposit(amount);
    }

    public void withdraw(int accountNumber, double amount)
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientBalanceException {

        Account account = findAccount(accountNumber);

        account.withdraw(amount);
    }

    public void transfer(
            int senderAccount,
            int receiverAccount,
            double amount)
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientBalanceException {

        Account sender = findAccount(senderAccount);
        Account receiver = findAccount(receiverAccount);

        if (senderAccount == receiverAccount) {
            throw new IllegalArgumentException(
                    "Sender and receiver cannot be the same."
            );
        }

        sender.transferTo(receiver, amount);
    }

    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");

        for (Account account : accounts.values()) {

            System.out.printf(
                    "Account: %d | Name: %s | Type: %s | Balance: ₹%.2f%n",
                    account.getAccountNumber(),
                    account.getHolderName(),
                    account.getAccountType(),
                    account.getBalance()
            );
        }
    }
}