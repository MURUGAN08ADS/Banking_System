It is an simple compiler based banking system using java .

OVERALL ARCHITECTURE

                    Main.java
                       |
                       ↓
               InputValidator
                       |
                       ↓
                    Bank
                       |
              HashMap<Integer, Account>
                       |
          +------------+------------+
          ↓                         ↓
   SavingsAccount            CurrentAccount
          |                         |
          +------------+------------+
                       ↓
                  Transaction

                  
The user interacts through a menu:

1. Create Account
2. Deposit Money
3. Withdraw Money
4. Check Balance
5. Transfer Money
6. Account Details
7. Transaction History
8. Display All Accounts
9. Exit

To run this on your vscode

1.Check the terminal location and make sure it is in a correct location
2.Then run it in terminal
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object {$_.FullName})

i)If everything is correct, nothing will be printed.
ii)It just create an class file for existing java file and store it in out folder

3.Then run this command
java -cp out com.codsoft.banking.Main

Expected output:================================
     JAVA BANKING SYSTEM
================================

1. Create Account
2. Deposit Money
3. Withdraw Money
4. Check Balance
5. Transfer Money
6. Account Details
7. Transaction History
8. Display All Accounts
9. Exit

Enter your choice:

