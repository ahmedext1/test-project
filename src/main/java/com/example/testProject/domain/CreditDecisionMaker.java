package com.example.testProject.domain;

/**
 * An interface of the credit decision algorithm.
 */
public interface CreditDecisionMaker {

    /**
     * Obtain a credit decision for given credit request amount and current customer debt amount.
     *
     * @param purchaseAmount the requested credit amount
     * @param customerDebt the customer's current debt
     * @return the credit decision
     */
    CreditDecision makeCreditDecision(int purchaseAmount, int customerDebt);

}
