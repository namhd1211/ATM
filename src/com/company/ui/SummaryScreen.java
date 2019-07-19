package com.company.ui;

import com.company.model.Account;

import java.util.Scanner;

class SummaryScreen {
    void summary_menu(Account account, String date, String withdraw) {
        System.out.println("----------------------------------------");
        System.out.println("----Summary----");
        System.out.println("Date  : " + date);
        System.out.println("Withdraw : " + withdraw);
        System.out.println("Balance : " + account.getBalance());

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                TransactionScreen transactionScreen = new TransactionScreen();
                transactionScreen.transaction_menu(account);
            case 2:
                WelcomeScreen welcomeScreen = new WelcomeScreen();
                welcomeScreen.welcome_menu();
        }
    }

    public void fund_transfer_summary(String destination, int transferAmount, String referenceNumber, Account account) {
        int choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Fund Transfer Summary");
            System.out.println("Destination model.Account : " + destination);
            System.out.println("Transfer Amount : $" + transferAmount);
            System.out.println("Reference Number : " + referenceNumber);
            System.out.println("Balance : " + account.getBalance());
            System.out.println("1. Transaction");
            System.out.println("2. Exit");
            System.out.print("Choose option[2]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    TransactionScreen transactionScreen = new TransactionScreen();
                    transactionScreen.transaction_menu(account);
                case 2:
                    System.exit(0);
            }
        } while (true);
    }
}
