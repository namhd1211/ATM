package com.company.service.impl.v1;

import com.company.entity.Account;
import com.company.entity.Transfer;
import com.company.service.TransferService;
import com.company.service.impl.v2.AccountServiceImplV2;
import com.company.service.impl.v2.AccountServiceV2;
import com.company.utils.Constant;
import com.company.utils.Utils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransferServiceImpl implements TransferService {
    private AccountServiceV2 accountServiceV2 = new AccountServiceImplV2();

    @Override
    public boolean isValidTransferAmount(String transferAmount, int balance) {
        if (!transferAmount.matches("^[0-9]*$")) {
            System.out.println("Invalid amount");
            return false;
        }
        if (Integer.valueOf(transferAmount) > 1000) {
            System.out.println("Maximum amount to transfer is $1000");
            return false;
        }

        if (Integer.valueOf(transferAmount) < 1) {
            System.out.println("Minimum  amount to transfer is $1");
            return false;
        }

        if (!(Integer.valueOf(transferAmount) % 10 == 0)) {
            System.out.println("Invalid amount");
            return false;
        }

        if (balance - Integer.valueOf(transferAmount) < 0) {
            System.out.println("Insufficient balance $" + transferAmount);
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidReferenceNumber(String referenceNumber) {
        if (!referenceNumber.isEmpty() && !referenceNumber.matches("^[0-9]*$")) {
            System.out.println("Invalid Reference Number");
            return false;
        }
        return true;
    }

    @Override
    public Account transfer(String destinationAccount, Account account, int transferAmount) {
        Utils utils = new Utils();
        Integer balance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
        account.setBalance("$" + (balance - transferAmount));
        accountServiceV2.updateAccountBalance(account.getAccountNumber(), account.getBalance(), Constant.FILE_NAME);
        return account;
    }

    @Override
    public void saveTransferTrans(Transfer transfer) {
        File file = new File(Constant.FILE_NAME_TRANSFER);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            FileReader fileReader = new FileReader(file);
            CSVReader reader = new CSVReader(fileReader);
            if (reader.readNext() == null) {
                String[] header = {"Sender", "Receiver", "Amount", "Created Date"};
                csvWriter.writeNext(header);
            }
            String[] body = {transfer.getSrcAccNumber(), transfer.getDesAccNumber(), transfer.getBalance(), transfer.getCreatedDate().toString()};
            csvWriter.writeNext(body);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
