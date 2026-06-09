package com.financeportal.finance_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    public BankAccount createAccount(Long userId, String accountType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setUser(user);
        account.setAccountType(accountType);
        account.setBalance(0.0);
        account.setAccountNumber(String.valueOf((long)(Math.random() * 9000000000L) + 1000000000L));

        return bankAccountRepository.save(account);
    }

    public List<BankAccount> getAccountsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bankAccountRepository.findByUser(user);
    }
}