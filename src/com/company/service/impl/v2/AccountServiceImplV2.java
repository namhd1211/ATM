package com.company.service.impl.v2;

import com.company.model.Account;
import com.company.model.Transaction;
import com.company.model.Transfer;
import com.company.utils.Constant;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<Account> collect = new ArrayList<>();
        try {
            BufferedReader reader = readFromFile(Constant.FILE_NAME);
            collect = reader.lines().skip(1).map(s -> {
                Account account = new Account();
                String[] line = s.split(",");
                account.setName(line[0].replaceAll("^\"|\"$", ""));
                account.setPin(line[1].replaceAll("^\"|\"$", ""));
                account.setBalance(line[2].replaceAll("^\"|\"$", ""));
                account.setAccountNumber(line[3].replaceAll("^\"|\"$", ""));
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

    @Override
    public List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        try {
            BufferedReader reader = readFromFile(Constant.FILE_NAME_WITHDRAW);
            List<Transaction> withDraw = reader.lines().skip(1).map(lines -> {
                String[] split = lines.split(",");
                if (accountNumber.equals(split[0].replaceAll("^\"|\"$", ""))) {
                    transaction.setType("WithDraw");
                    transaction.setAmount(split[3]);
                    transaction.setDateTime(LocalDateTime.parse(split[2].replaceAll("^\"|\"$", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    transaction.setBalance(split[1]);
                }
                return transaction;
            }).collect(Collectors.toList());
            BufferedReader reader1 = readFromFile(Constant.FILE_NAME_TRANSFER);
            List<Transaction> transfer = reader1.lines().skip(1).map(lines -> {
                String[] split = lines.split(",");
                if (accountNumber.equals(split[0].replaceAll("^\"|\"$", ""))) {
                    transaction.setAmount(split[2]);
                    transaction.setDateTime(LocalDateTime.parse(split[3].replaceAll("^\"|\"$", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    transaction.setType("Transfer");
                    transaction.setBalance("");
                }
                return transaction;
            }).collect(Collectors.toList());
            transactionList.addAll(withDraw);
            transactionList.addAll(transfer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    private BufferedReader readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = new FileInputStream(file);
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
