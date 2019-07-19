package com.company.service.impl.v1;

import com.company.entity.Account;
import com.company.service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> listAccount() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Jane Doe", "932012", "$30", "112244"));
        accounts.add(new Account("John Doe", "012108", "$100", "112233"));
        return accounts;
    }

    @Override
    public Account getAccountByAccNumber(String accNumber) {
        List<Account> accounts = listAccount();
        return accounts.stream().filter(account -> account.getAccountNumber().equals(accNumber)).findAny().orElse(null);
    }

}
