package com.company.service;

import com.company.entity.Account;
import com.company.entity.Transfer;

public interface TransferService {
    boolean isValidTransferAmount(String transferAmount, int balance);

    boolean isValidReferenceNumber(String referenceNumber);

    Account transfer(String destinationAccount, Account account, int transferAmount);

    void saveTransferTrans(Transfer transfer);
}
