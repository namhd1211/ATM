package com.company.service;

import com.company.model.Account;
import com.company.model.Transfer;

public interface TransferService {
    boolean isValidTransferAmount(String transferAmount, int balance);

    boolean isValidReferenceNumber(String referenceNumber);

    Account transfer(String destinationAccount, Account account, int transferAmount);

    void saveTransferTrans(Transfer transfer);
}
