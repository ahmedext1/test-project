package com.example.testProject.repository;

/**
 * An interface for handling the persistence of customer's debt.
 */
public interface CustomerDebtRepository {

    /**
     * Fetching current debt for the customer of given email address.
     *
     * @param email the primary identifier of the customer
     * @return the customer's current debt
     */
    int fetchCustomerDebtForEmail(String email);

    /**
     * Add customer's debt.
     *
     * @param CustomerDebt the added customer's debt
     */
    void addCustomerDebt(CustomerDebt customerDebt);

}
