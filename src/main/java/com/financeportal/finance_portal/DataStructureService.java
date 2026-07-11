package com.financeportal.finance_portal;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DataStructuresService {

    // HashMap — maps account numbers to BankAccount objects
    private HashMap<String, BankAccount> accountCache = new HashMap<>();

    public void cacheAccount(BankAccount account) {
        accountCache.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccountFromCache(String accountNumber) {
        return accountCache.get(accountNumber);
    }

    // Priority Queue — processes highest loan amounts first
    private PriorityQueue<LoanApplication> loanQueue = new PriorityQueue<>(
            (a, b) -> Double.compare(b.getLoanAmount(), a.getLoanAmount())
    );

    public void addLoanToQueue(LoanApplication loan) {
        loanQueue.offer(loan);
    }

    public LoanApplication processNextLoan() {
        return loanQueue.poll();
    }

    // Queue — processes transactions in order
    private Queue<Transaction> pendingTransactions = new LinkedList<>();

    public void addPendingTransaction(Transaction transaction) {
        pendingTransactions.offer(transaction);
    }

    public Transaction processNextTransaction() {
        return pendingTransactions.poll();
    }

    // Stack — undo history for transactions
    private Stack<Transaction> transactionHistory = new Stack<>();

    public void pushTransaction(Transaction transaction) {
        transactionHistory.push(transaction);
    }

    public Transaction undoLastTransaction() {
        return transactionHistory.pop();
    }

    // LinkedList — chronological transaction history
    private LinkedList<Transaction> transactionChain = new LinkedList<>();

    public void addTransactionToChain(Transaction transaction) {
        transactionChain.addLast(transaction);
    }

    public LinkedList<Transaction> getTransactionChain() {
        return transactionChain;
    }

    // Array — fixed list of transaction categories
    private String[] transactionCategories = {
            "DEPOSIT", "WITHDRAWAL", "TRANSFER",
            "LOAN_PAYMENT", "INTEREST", "FEE"
    };

    public String[] getTransactionCategories() {
        return transactionCategories;
    }

    // Set — tracks unique users who accessed a loan file
    private Set<Long> loanAccessLog = new HashSet<>();

    public void logLoanAccess(Long userId) {
        loanAccessLog.add(userId);
    }

    public Set<Long> getLoanAccessLog() {
        return loanAccessLog;
    }
}