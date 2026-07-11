package com.financeportal.finance_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Transaction deposit(Long accountId, Double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setBankAccount(account);
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setBalanceAfter(account.getBalance());

        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long accountId, Double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setBankAccount(account);
        transaction.setTransactionType("WITHDRAWAL");
        transaction.setAmount(amount);
        transaction.setBalanceAfter(account.getBalance());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return transactionRepository.findByBankAccount(account);
    }
}