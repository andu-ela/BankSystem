import java.util.List;
import java.util.Scanner;

public class BankSystemApp {
    private static Bank bank;

    public static void main(String[] args) {
        initializeBank();
        startBankSystem();
    }

    private static void initializeBank() {
        bank = new Bank("Bank Name", 10.0, 0.05); 
    }

    private static void startBankSystem() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
        	System.out.println("\nChoose an action:");
        	System.out.println("1. Create an account");
        	System.out.println("2. Perform a transaction");
        	System.out.println("3. Deposit money");
        	System.out.println("4. Withdraw money");
        	System.out.println("5. Check balance");
        	System.out.println("6. View transaction history");
        	System.out.println("7. Display account list");
        	System.out.println("8. Exit");


            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performTransaction(scanner);
                    break;
                case 3:
                    depositMoney(scanner);
                    break;
                case 4:
                    withdrawMoney(scanner);
                    break;
                case 5:
                    checkBalance(scanner);
                    break;
                case 6:
                    viewTransactionHistory(scanner);
                    break;
                case 7:
                    displayAccountList();
                    break;
                case 8:
                    isRunning = false;
                    break;
                default:
                	System.out.println("Invalid action, please choose again.");
            }
        }

        System.out.println("Thank you for using the Bank System. Feel free to return whenever you want!");
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Username:");
        String userName = scanner.nextLine();
        System.out.println("Initial balance:");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); 

        String accountId = generateAccountId(); 
        Account account = new Account(accountId, userName, initialBalance);
        bank.addAccount(account);

        System.out.println("Account created successfully!");
    }

    private static void performTransaction(Scanner scanner) {
    	System.out.println("Enter the ID of the originating account:");
    	String fromAccountId = scanner.nextLine();
    	System.out.println("Enter the ID of the resulting account:");
    	String toAccountId = scanner.nextLine();
    	System.out.println("Enter the amount for the transaction:");
    	double amount = scanner.nextDouble();
    	scanner.nextLine(); 
    	System.out.println("Enter the reason for the transaction:");
    	String reason = scanner.nextLine();

  
        Account fromAccount = findAccountById(fromAccountId);
        Account toAccount = findAccountById(toAccountId);

        if (fromAccount != null && toAccount != null) {
        	try {
        	    bank.performTransaction(fromAccount, toAccount, amount, reason);
        	    System.out.println("The transaction was successfully completed!");
        	} catch (InsufficientFundsException e) {
        	    System.out.println("Insufficient funds to complete the transaction.");
        	}

        } else {
        	System.out.println("The account with ID: " + fromAccountId + " or " + toAccountId + " was not found in the bank.");
;
        }
    }

    private static void depositMoney(Scanner scanner) {
        System.out.println("Enter the account ID:");
        String accountId = scanner.nextLine();
        System.out.println("Enter the amount to deposit:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

   
        Account account = findAccountById(accountId);

        if (account != null) {
            bank.deposit(account, amount);
            System.out.println("Money deposited successfully!");
        } else {
        	System.out.println("Account not found.");
        }
    }


private static void withdrawMoney(Scanner scanner) {
    System.out.println("Enter the account ID:");
    String accountId = scanner.nextLine();
    System.out.println("Enter the amount to withdraw:");
    double amount = scanner.nextDouble();
    scanner.nextLine(); 

  
    Account account = findAccountById(accountId);

    if (account != null) {
        bank.withdraw(account, amount);
        System.out.println("Money withdrawn successfully!");
    } else {
    	System.out.println("Account not found.");
    }
}

    private static void checkBalance(Scanner scanner) {
        System.out.println("Enter the account ID:");
        String accountId = scanner.nextLine();

   
        Account account = findAccountById(accountId);
        if (account != null) {
            double balance = account.getBalance();
            System.out.println("The balance of account " + accountId + " is: $" + balance);
        } else {
        	System.out.println("Account not found.");
        }
    }

    private static void viewTransactionHistory(Scanner scanner) {
        System.out.println("Shkruaj ID-në e llogarisë për të shikuar historinë e transaksioneve:");
        String accountId = scanner.nextLine();

      
        Account account = findAccountById(accountId);

        if (account != null) {
            List<Transaction> transactions = account.getTransactions();
            if (transactions != null && !transactions.isEmpty()) {
            	System.out.println("Transaction history for account " + accountId + ":");
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            } else {
            	System.out.println("There are no transactions for this account.");
            }
        } else {
        	System.out.println("Account not found.");
        }
    }

    private static void displayAccountList() {
        List<Account> accounts = bank.getAccounts();
        if (!accounts.isEmpty()) {
        	System.out.println("List of accounts in the bank:");
            for (Account account : accounts) {
                System.out.println("Id"+account.getAccountId()+ "Username:" + account.getUserName());
            }
        } else {
        	System.out.println("There are no accounts in the bank.");
        }
    }

   

    private static String generateAccountId() {
        int randomNum = (int) (Math.random() * 1000);
 
        String accountId = "ACC" + randomNum;
        return accountId;
    }


    private static Account findAccountById(String accountId) {
 
        for (Account account : bank.getAccounts()) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
    }
		return null;

    }
}


