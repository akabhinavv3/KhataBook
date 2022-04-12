package com.example.khatabook;

public class Model {
    String amount,amountDetails,date;

    public Model()
    {

    }

    public Model(String amount, String amountDetails, String date) {
        this.amount = amount;
        this.amountDetails = amountDetails;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountDetails() {
        return amountDetails;
    }

    public void setAmountDetails(String amountDetails) {
        this.amountDetails = amountDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
