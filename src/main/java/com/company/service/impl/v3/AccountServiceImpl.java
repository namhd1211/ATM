package com.company.service.impl.v3;

import com.company.entity.Account;
import com.company.repository.AccountRepository;
import com.company.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> listAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountByAccNumber(String accNumber) {
        return accountRepository.findAccountByAccountNumber(accNumber);
    }
}
