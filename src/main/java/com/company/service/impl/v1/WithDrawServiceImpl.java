package com.company.service.impl.v1;

import com.company.entity.Account;
import com.company.entity.WithDraw;
import com.company.service.WithDrawService;
import com.company.utils.Constant;
import com.company.utils.Utils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WithDrawServiceImpl implements WithDrawService {

    @Override
    public boolean isValidWithDrawAmount(String withdrawAmount, int balance) {
        if (!withdrawAmount.matches("^[0-9]*$")) {
            System.out.println("Invalid amount");
            return false;
        }
        if (Integer.valueOf(withdrawAmount) > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            return false;
        }
        if (!(Integer.valueOf(withdrawAmount) % 10 == 0)) {
            System.out.println("Invalid amount");
            return false;
        }
        if (balance - Integer.valueOf(withdrawAmount) < 0) {
            System.out.println("Insufficient balance $" + withdrawAmount);
            return false;
        }
        return true;
    }

    @Override
    public Account withDraw(Account account, int withDraw) {
        Utils utils = new Utils();
        Integer intBalance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
        account.setBalance("$" + (intBalance - withDraw));
        return account;
    }

    @Override
    public void saveWithDrawTrans(WithDraw withDraw) {
        File file = new File(Constant.FILE_NAME_WITHDRAW);
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
                String[] header = {"AccountNumber", "Balance", "CreatedDate", "WithDrawAmount"};
                csvWriter.writeNext(header);
            }
            String[] body = {withDraw.getAccountNumber(), withDraw.getBalance(), withDraw.getCreatedDate(), withDraw.getWithDrawAmount()};
            csvWriter.writeNext(body);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
