package edu.neumont.mgt101.models;

public class Transaction {
    private TransactionType transactionType;
    private String transactionName;
    private String transactionDescription;
    private double moneyAmount;

    //region Getters and Setters
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        if (transactionType == null)
        {
            throw new IllegalArgumentException("transactionType cannot be null");
        }
        this.transactionType = transactionType;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        if (transactionName == null || transactionName.isBlank())
        {
            throw new IllegalArgumentException("transactionName cannot be null or blank");
        }
        this.transactionName = transactionName;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        if (transactionDescription == null || transactionDescription.isBlank())
        {
            throw new IllegalArgumentException("transactionDescription cannot be null or blank");
        }
        this.transactionDescription = transactionDescription;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount)
    {
        if  (moneyAmount < 0)
        {
            throw new IllegalArgumentException("moneyAmount cannot be negative");
        }
        this.moneyAmount = moneyAmount;
    }
    //endregion


    public Transaction(TransactionType transactionType, String transactionName, String transactionDescription, double moneyAmount) {
        setTransactionType(transactionType);
        setTransactionName(transactionName);
        setTransactionDescription(transactionDescription);
        setMoneyAmount(moneyAmount);
    }
    public Transaction(TransactionType transactionType, String transactionName, double moneyAmount) {
        setTransactionType(transactionType);
        setTransactionName(transactionName);
        setTransactionDescription(transactionDescription);
        setMoneyAmount(moneyAmount);
    }

    @Override
    public String toString() {
        if (transactionDescription == null || transactionDescription.isBlank())
            return
                    "Type of Transaction: " + transactionType +
                    ", Name: " + transactionName + '\'' +
                    ", Amount: " + moneyAmount;

        else
        {
            return
                    "Type of Transaction: " + transactionType +
                    ", Name: " + transactionName + '\'' +
                    ", Description: " + transactionDescription + '\'' +
                    ", Amount: " + moneyAmount;
        }
    }
}
