package com.financeportal.finance_portal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByBankAccount(BankAccount bankAccount);


}
