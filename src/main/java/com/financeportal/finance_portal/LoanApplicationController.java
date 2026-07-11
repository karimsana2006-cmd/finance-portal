package com.financeportal.finance_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan(@RequestBody Map<String, String> request) {
        Long userId = Long.valueOf(request.get("userId"));
        Double loanAmount = Double.valueOf(request.get("loanAmount"));
        Integer termMonths = Integer.valueOf(request.get("termMonths"));
        try {
            LoanApplication loan = loanApplicationService.applyForLoan(userId, loanAmount, termMonths);
            return ResponseEntity.ok(loan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserLoans(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(loanApplicationService.getUserLoans(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<?> approveLoan(@PathVariable Long loanId) {
        try {
            return ResponseEntity.ok(loanApplicationService.approveLoan(loanId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/reject/{loanId}")
    public ResponseEntity<?> rejectLoan(@PathVariable Long loanId) {
        try {
            return ResponseEntity.ok(loanApplicationService.rejectLoan(loanId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}