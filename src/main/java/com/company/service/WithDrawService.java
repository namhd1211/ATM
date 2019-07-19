package com.company.service;

import com.company.entity.Account;
import com.company.entity.WithDraw;

public interface WithDrawService {
    boolean isValidWithDrawAmount(String withdrawAmount, int balance);

    Account withDraw(Account account, int withDraw);

    void saveWithDrawTrans(WithDraw withDraw);
}
