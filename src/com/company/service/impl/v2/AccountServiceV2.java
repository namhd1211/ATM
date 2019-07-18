package com.company.service.impl.v2;

import com.company.service.AccountService;

public interface AccountServiceV2 extends AccountService {
    void updateAccountBalance(String accountNumner, String balance, String fileName);
}
