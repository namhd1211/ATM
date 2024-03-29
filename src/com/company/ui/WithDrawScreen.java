package com.company.ui;

import com.company.model.Account;
import com.company.model.WithDraw;
import com.company.service.WithDrawService;
import com.company.service.impl.WithDrawServiceImpl;
import com.company.service.impl.v2.AccountServiceImplV2;
import com.company.service.impl.v2.AccountServiceV2;
import com.company.utils.Constant;
import com.company.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class WithDrawScreen {
    private WithDrawService withDrawService = new WithDrawServiceImpl();
    private AccountServiceV2 accountService = new AccountServiceImplV2();

    void withdraw_menu(Account account) {
        String choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("----WithDraw Screen----");
            System.out.println("1. $10");
            System.out.println("2. $50");
            System.out.println("3. $100");
            System.out.println("4. Other");
            System.out.println("5. Back");
            System.out.print("Please choose option[5]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    if (isValidWithDraw(account, "10")) {
                        withDraw(account, 10);
                    }
                    break;
                case "2":
                    if (isValidWithDraw(account, "10")) {
                        withDraw(account, 50);
                    }
                    break;
                case "3":
                    if (isValidWithDraw(account, "100")) {
                        withDraw(account, 100);
                    }
                    break;
                case "4":
                    withdraw_other_menu(account);
                    break;
                case "":
                case "5":
                    TransactionScreen transactionScreen = new TransactionScreen();
                    transactionScreen.transaction_menu(account);
                    break;
            }
        } while (!choice.equals("5"));
    }

    private void withdraw_other_menu(Account account) {
        boolean validWithDraw;
        do {
            System.out.print("Other Withdraw\n" +
                    "Enter amount to withdraw: ");
            Scanner scanner = new Scanner(System.in);
            String otherWithDraw = scanner.next();
            validWithDraw = isValidWithDraw(account, otherWithDraw);
            if (validWithDraw) {
                withDraw(account, Integer.valueOf(otherWithDraw));
            }
        } while (!validWithDraw);
    }

    private void withDraw(Account account, int withDraw) {
        Account account1 = withDrawService.withDraw(account, withDraw);
        accountService.updateAccountBalance(account.getAccountNumber(), account.getBalance(), Constant.FILE_NAME);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        withDrawService.saveWithDrawTrans(new WithDraw(account.getAccountNumber(), account.getBalance(), String.valueOf(withDraw), now.format(dateTimeFormatter)));
        SummaryScreen summaryScreen = new SummaryScreen();
        summaryScreen.summary_menu(account1, now.format(dateTimeFormatter), "$" + withDraw);
    }

    private boolean isValidWithDraw(Account account, String withDraw) {
        Utils utils = new Utils();
        int balance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
        return withDrawService.isValidWithDrawAmount(withDraw, balance);
    }
}
