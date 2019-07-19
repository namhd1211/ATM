package com.company.ui;

import com.company.model.Account;
import com.company.model.Transaction;
import com.company.service.impl.v2.AccountServiceImplV2;
import com.company.service.impl.v2.AccountServiceV2;

import java.util.List;
import java.util.Scanner;

class TransactionScreen {
    private AccountServiceV2 accountServiceV2 = new AccountServiceImplV2();
    void transaction_menu(Account account) {
        String choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("----Transaction Screen----");
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Transaction History");
            System.out.println("4. Exit");
            System.out.print("Please choose option[4]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            switch (choice) {
                case "1":
                    WithDrawScreen withDrawScreen = new WithDrawScreen();
                    withDrawScreen.withdraw_menu(account);
                    break;
                case "2":
                    FundTransferScreen transferScreen = new FundTransferScreen();
                    transferScreen.fund_transfer_account(account);
                    break;
                case "3":
                    transactionHistoryScreen(account.getAccountNumber());
                    break;
                case "":
                case "4":
                    welcomeScreen.welcome_menu();
                    break;
            }
        } while (!choice.equals("4"));
    }

    private void transactionHistoryScreen(String accountNumber) {
        List<Transaction> transactions = accountServiceV2.getTransactions(accountNumber);
        transactions.forEach(System.out::println);
    }
}
