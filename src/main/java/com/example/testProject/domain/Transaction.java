package com.example.testProject.domain;

public class Transaction {

    private int purchaseAmount;
    private CreditDecision creditDecision;

    public Transaction(int purchaseAmount, CreditDecision creditDecision) {
        this.purchaseAmount = purchaseAmount;
        this.creditDecision = creditDecision;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public CreditDecision getCreditDecision() {
        return creditDecision;
    }
}
