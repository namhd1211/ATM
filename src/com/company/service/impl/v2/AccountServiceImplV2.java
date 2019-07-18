package com.company.service.impl.v2;

import com.company.model.Account;
import com.company.utils.Constant;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImplV2 implements AccountServiceV2 {

    @Override
    public List<Account> listAccount() {
        return listAllAccountFromFile();
    }

    @Override
    public Account getAccountByAccNumber(String accNumber) {
        List<Account> accounts = listAccount();
        return accounts.stream().filter(account -> account.getAccountNumber().replaceAll("^\"|\"$", "").equals(accNumber)).findAny().orElse(null);
    }

    private List<Account> listAllAccountFromFile() {
        File file = new File(Constant.FILE_NAME);
        InputStream inputStream;
        List<Account> collect = new ArrayList<>();
        try {
            inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            collect = reader.lines().skip(1).map(s -> {
                Account account = new Account();
                String[] line = s.split(",");
                account.setName(line[0]);
                account.setPin(line[1]);
                account.setBalance(line[2]);
                account.setAccountNumber(line[3]);
                return account;
            }).collect(Collectors.toList());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collect;
    }

    @Override
    public void updateAccountBalance(String accountNumber, String balance, String fileName) {
        File file = new File(Constant.FILE_NAME);
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            List<String[]> body = reader.readAll();
            for (String[] strings : body) {
                for (int j = 0; j < strings.length; j++) {
                    if (strings[j].replaceAll("^\"|\"$", "").equals(accountNumber)) {
                        strings[j - 1] = balance;
                    }
                }
            }
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(body);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("cannot read csv file");
        }
    }
}
