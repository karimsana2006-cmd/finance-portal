package com.financeportal.finance_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String accountType = request.get("accountType");
        try {
            BankAccount savedAccount = bankAccountService.createAccount(Long.valueOf(userId), accountType);
            return ResponseEntity.ok(savedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAccounts(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(bankAccountService.getAccountsByUser(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}