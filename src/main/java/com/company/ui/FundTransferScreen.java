package com.company.ui;


import com.company.entity.Account;
import com.company.entity.Transfer;
import com.company.service.AccountValidationService;
import com.company.service.TransferService;
import com.company.service.impl.v1.TransferServiceImpl;
import com.company.service.impl.v2.AccountValidationServiceImpl;
import com.company.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class FundTransferScreen {
    private AccountValidationService validationService = new AccountValidationServiceImpl();
    private TransferService transferService = new TransferServiceImpl();

    void fund_transfer_account(Account account) {
        boolean validAccountNumber;
        boolean existedAccount;
        do {
            System.out.println("----------------------------------------");
            System.out.print("Please enter destination account and \n" +
                    "press enter to continue or \n" +
                    "press cancel (Esc) to go back to Transaction: ");
            Scanner scanner = new Scanner(System.in);
            String destinationAccount = scanner.next();
            validAccountNumber = validationService.isValidAccountNumber(destinationAccount);
            existedAccount = validationService.isExistedAccount(destinationAccount);
            if (validAccountNumber && existedAccount) {
                fund_transfer_transferAmount(account, destinationAccount);
            }
        } while (!(validAccountNumber && existedAccount));

    }

    private void fund_transfer_transferAmount(Account account, String destination) {
        boolean validTransferAmount;
        do {
            System.out.println("----------------------------------------");
            System.out.print("Please enter transfer amount and \n" +
                    "press enter to continue or \n" +
                    "press cancel (Esc) to go back to Transaction: ");
            Scanner scanner = new Scanner(System.in);
            String transferAmount = scanner.next();
            Utils utils = new Utils();
            int balance = Integer.valueOf(utils.formatCurrency(account.getBalance().replaceAll("^\"|\"$", "")));
            validTransferAmount = transferService.isValidTransferAmount(transferAmount, balance);
            if (validTransferAmount) {
                fund_transfer_referenceNumber(destination, Integer.valueOf(transferAmount), account);
            }
        } while (!validTransferAmount);
    }

    private void fund_transfer_referenceNumber(String destination, int transferAmount, Account account) {
        boolean validReferenceNumber = false;
        do {
            System.out.println("----------------------------------------");
            System.out.print("Please enter reference number (Optional) and \n" +
                    "press enter to continue or \n" +
                    "press cancel (Esc) to go back to Transaction: ");
            Scanner scanner = new Scanner(System.in);
            String referenceId = scanner.nextLine();
            if (!referenceId.isEmpty()) {
                validReferenceNumber = transferService.isValidReferenceNumber(referenceId);
            }
            fund_transfer_confirm(destination, transferAmount, referenceId, account);
        } while (!validReferenceNumber);

    }

    private void fund_transfer_confirm(String destination, int transferAmount, String referenceNumber, Account account) {
        String choice;
        do {
            System.out.println("----------------------------------------");
            System.out.println("Transfer Confirmation");
            System.out.println("Destination entity.Account : " + destination);
            System.out.println("Transfer Amount : $" + transferAmount);
            System.out.println("Reference Number : " + referenceNumber);
            System.out.println("1. Confirm Trx");
            System.out.println("2. Cancel Trx");
            System.out.print("Choose option[2]: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Account transferAccount = transferService.transfer(destination, account, transferAmount);
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    Transfer transfer = new Transfer(account.getAccountNumber(), destination,String.valueOf(transferAmount), now.format(dateTimeFormatter));
                    transferService.saveTransferTrans(transfer);
                    SummaryScreen summaryScreen = new SummaryScreen();
                    summaryScreen.fund_transfer_summary(destination, transferAmount, referenceNumber, transferAccount);
                case "":
                    fund_transfer_account(account);
                case "2":
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.welcome_menu();
            }
        } while (!choice.equals("2"));
    }


}
