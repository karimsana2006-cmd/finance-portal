package com.financeportal.finance_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String, String> request) {
        Long accountId = Long.valueOf(request.get("accountId"));
        Double amount = Double.valueOf(request.get("amount"));
        try {
            Transaction saved = transactionService.deposit(accountId, amount);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String, String> request) {
        Long accountId = Long.valueOf(request.get("accountId"));
        Double amount = Double.valueOf(request.get("amount"));
        try {
            Transaction saved = transactionService.withdraw(accountId, amount);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<?> getHistory(@PathVariable Long accountId) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionHistory(accountId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}