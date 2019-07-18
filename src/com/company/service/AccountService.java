package com.company.service;

import com.company.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> listAccount();

    Account getAccountByAccNumber(String accNumber);

}
