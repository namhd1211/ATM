package com.company.service.impl.v2;

import com.company.entity.Transaction;
import com.company.service.AccountService;

import java.util.List;

public interface AccountServiceV2 extends AccountService {
    void updateAccountBalance(String accountNumner, String balance, String fileName);

    List<Transaction> getTransactions(String accountNumber);
}
