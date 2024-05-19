import java.util.ArrayList;
import java.util.List;

public class Account {
    
    private String accountId;
    private String userName;
    private double balance;
    private List<Transaction> transactions; 

    public Account(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
        this.transactions = new ArrayList<>(); 
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Not enough funds!");
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public List<Transaction> getTransactions(){
    	return transactions;
    }

}
