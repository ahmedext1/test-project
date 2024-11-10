package com.example.testProject.repository;

import com.example.testProject.domain.CreditDecision;
import com.example.testProject.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CreditHistoryRepository {

    Map<String, List<Transaction>> transactionHistory = new HashMap<>();

    public Collection lookupTransactions(String email) {
        List<Transaction> transactions = new ArrayList<>();
        for (Map.Entry<String, List<Transaction>> entry : transactionHistory.entrySet()) {
            if (entry.getKey().equals(email)) {
                transactions = entry.getValue();
                break;
            }
        }
        return transactions.isEmpty() ? new ArrayList<>() : transactions;
    }


    public Collection lookupTransactions(String email, String reason) {
        Collection<Transaction> transactions = new ArrayList<>(lookupTransactions(email));
        transactions.removeIf(t -> !t.getCreditDecision().getReason().equals(reason));
        return transactions;
    }


    public void persistTransaction(String email, int purchaseAmount, CreditDecision decision) {
        List<Transaction> transactions = null;
        for (Map.Entry<String, List<Transaction>> entry : transactionHistory.entrySet()) {
            if (entry.getKey().equals(email)) {
                transactions = entry.getValue();
                break;
            }
        }
        if (transactions == null) {
            transactions = new ArrayList<>();
            transactionHistory.put(email, transactions);
        }
        transactions.add(new Transaction(purchaseAmount, decision));
    }
}
