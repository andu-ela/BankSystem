import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.setBankName(bankName);
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.setTransactionFlatFeeAmount(transactionFlatFeeAmount);
        this.setTransactionPercentFeeValue(transactionPercentFeeValue);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void performTransaction(Account fromAccount, Account toAccount, double amount, String reason) throws InsufficientFundsException {
       
        if (fromAccount == null || toAccount == null) {
        	throw new IllegalArgumentException("Origin or destination account not found.");
        }

        if (fromAccount.getBalance() < amount) {
        	throw new InsufficientFundsException("Insufficient funds to perform the transaction.");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        Transaction transaction = new Transaction(amount, fromAccount.getAccountId(), toAccount.getAccountId(), reason);
        fromAccount.addTransaction(transaction);

        double transactionFee = calculateTransactionFee(amount);
        this.totalTransactionFeeAmount += transactionFee;
        this.totalTransferAmount += amount;

        if (transactionFee > 0) {
            fromAccount.withdraw(transactionFee);
        }
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
    }

    private double calculateTransactionFee(double amount) {
        if (this.transactionPercentFeeValue > 0) {
            return amount * this.transactionPercentFeeValue / 100;
        } else {
            return this.transactionFlatFeeAmount;
        }
    }


    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }

    public double checkBalance(Account account) {
        return account.getBalance();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getTransactionFlatFeeAmount() {
		return transactionFlatFeeAmount;
	}

	public void setTransactionFlatFeeAmount(double transactionFlatFeeAmount) {
		this.transactionFlatFeeAmount = transactionFlatFeeAmount;
	}

	public double getTransactionPercentFeeValue() {
		return transactionPercentFeeValue;
	}

	public void setTransactionPercentFeeValue(double transactionPercentFeeValue) {
		this.transactionPercentFeeValue = transactionPercentFeeValue;
	}

}

