package com.codsoft.banking.model;

public class SavingsAccount extends Account {

    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(
            int accountNumber,
            String holderName,
            double initialBalance) {

        super(accountNumber, holderName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    public static double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
}