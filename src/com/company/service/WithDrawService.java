package com.company.service;

import com.company.model.Account;
import com.company.model.WithDraw;

public interface WithDrawService {
    boolean isValidWithDrawAmount(String withdrawAmount, int balance);

    Account withDraw(Account account, int withDraw);

    void saveWithDrawTrans(WithDraw withDraw);
}
