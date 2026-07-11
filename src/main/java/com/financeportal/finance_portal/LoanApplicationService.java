package com.financeportal.finance_portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    public LoanApplication applyForLoan(Long userId, Double loanAmount, Integer termMonths) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoanApplication application = new LoanApplication();
        application.setUser(user);
        application.setLoanAmount(loanAmount);
        application.setTermMonths(termMonths);

        return loanApplicationRepository.save(application);
    }

    public List<LoanApplication> getUserLoans(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return loanApplicationRepository.findByUser(user);
    }

    public LoanApplication approveLoan(Long loanId) {
        LoanApplication ap = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        ap.setStatus("APPROVED");
        return loanApplicationRepository.save(ap);
    }

    public LoanApplication rejectLoan(Long loanId) {
        LoanApplication ap = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        ap.setStatus("REJECTED");
        return loanApplicationRepository.save(ap);
    }
}