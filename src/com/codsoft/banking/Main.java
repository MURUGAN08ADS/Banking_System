package com.codsoft.banking;

import com.codsoft.banking.exception.*;
import com.codsoft.banking.model.Account;
import com.codsoft.banking.service.Bank;
import com.codsoft.banking.util.InputValidator;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final InputValidator input =
            new InputValidator(scanner);

    private static final Bank bank =
            new Bank();

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     JAVA BANKING SYSTEM");
        System.out.println("================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = input.readInt(
                    "Enter your choice: "
            );

            System.out.println();

            try {

                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        deposit();
                        break;

                    case 3:
                        withdraw();
                        break;

                    case 4:
                        checkBalance();
                        break;

                    case 5:
                        transfer();
                        break;

                    case 6:
                        accountDetails();
                        break;

                    case 7:
                        transactionHistory();
                        break;

                    case 8:
                        bank.displayAllAccounts();
                        break;

                    case 9:
                        running = false;
                        System.out.println(
                                "Thank you for using the Banking System!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid menu choice."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("================================");
        System.out.println("             MENU");
        System.out.println("================================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Transfer Money");
        System.out.println("6. Account Details");
        System.out.println("7. Transaction History");
        System.out.println("8. Display All Accounts");
        System.out.println("9. Exit");
        System.out.println("================================");
    }

    private static void createAccount()
            throws InvalidAmountException {

        String name = input.readString(
                "Enter account holder name: "
        );

        String type = input.readString(
                "Enter account type (Savings/Current): "
        );

        double initialDeposit = input.readDouble(
                "Enter initial deposit: ₹"
        );

        Account account =
                bank.createAccount(
                        name,
                        type,
                        initialDeposit
                );

        System.out.println(
                "\nAccount created successfully!"
        );

        account.displayAccountDetails();
    }

    private static void deposit()
            throws AccountNotFoundException,
            InvalidAmountException {

        int accountNumber =
                input.readInt(
                        "Enter account number: "
                );

        double amount =
                input.readDouble(
                        "Enter deposit amount: ₹"
                );

        bank.deposit(
                accountNumber,
                amount
        );

        System.out.println(
                "Amount deposited successfully."
        );
    }

    private static void withdraw()
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientBalanceException {

        int accountNumber =
                input.readInt(
                        "Enter account number: "
                );

        double amount =
                input.readDouble(
                        "Enter withdrawal amount: ₹"
                );

        bank.withdraw(
                accountNumber,
                amount
        );

        System.out.println(
                "Amount withdrawn successfully."
        );
    }

    private static void checkBalance()
            throws AccountNotFoundException {

        int accountNumber =
                input.readInt(
                        "Enter account number: "
                );

        Account account =
                bank.findAccount(accountNumber);

        System.out.printf(
                "Current Balance: ₹%.2f%n",
                account.getBalance()
        );
    }

    private static void transfer()
            throws AccountNotFoundException,
            InvalidAmountException,
            InsufficientBalanceException {

        int sender =
                input.readInt(
                        "Enter sender account number: "
                );

        int receiver =
                input.readInt(
                        "Enter receiver account number: "
                );

        double amount =
                input.readDouble(
                        "Enter transfer amount: ₹"
                );

        bank.transfer(
                sender,
                receiver,
                amount
        );

        System.out.println(
                "Money transferred successfully."
        );
    }

    private static void accountDetails()
            throws AccountNotFoundException {

        int accountNumber =
                input.readInt(
                        "Enter account number: "
                );

        Account account =
                bank.findAccount(accountNumber);

        account.displayAccountDetails();
    }

    private static void transactionHistory()
            throws AccountNotFoundException {

        int accountNumber =
                input.readInt(
                        "Enter account number: "
                );

        Account account =
                bank.findAccount(accountNumber);

        account.displayTransactions();
    }
}