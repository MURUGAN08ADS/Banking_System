package com.codsoft.banking.model;

public class CurrentAccount extends Account {

    public CurrentAccount(
            int accountNumber,
            String holderName,
            double initialBalance) {

        super(accountNumber, holderName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }
}