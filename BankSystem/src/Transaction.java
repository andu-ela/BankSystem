public class Transaction {
    private double amount;
    private String fromAccountId;
    private String toAccountId;
    private String reason;

    public Transaction(double amount, String fromAccountId, String toAccountId, String reason) {
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Transaction: [Amount: $" + amount + ", From Account ID: " + fromAccountId + ", To Account ID: " + toAccountId + ", Reason: " + reason + "]";
    }
}
