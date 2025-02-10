package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.service.BankAccountService;
import com.bank.service.CardService;
import com.bank.service.CardTransferService;
import com.bank.service.TransferService;
import com.bank.service.exception.*;
import com.bank.service.impl.BankAccountServiceImpl;
import com.bank.service.impl.CardServiceImpl;
import com.bank.service.impl.CardTransferServiceImpl;
import com.bank.service.impl.TransferServiceImpl;
import com.bank.view.security.AuthenticatedCustomer;

public class CardPage {
    private CardService cardService;
    private BankAccountService bankAccountService;
    private Show show;
    private TransferService transferService;
    private CardTransferService cardTransferService;

    int key10 = 0;


    public CardPage() {
        this.cardService = new CardServiceImpl();
        this.bankAccountService = new BankAccountServiceImpl();
        this.show = new Show();
        this.transferService = new TransferServiceImpl();
        this.cardTransferService = new CardTransferServiceImpl();
    }


    public void showMenu() {
        String accountNumber = bankAccountService.getAccountNumber(AuthenticatedCustomer.logedInCustomer);
        String sourceCardNumber = null;
        try {
          sourceCardNumber  = cardService.getCardNumber(accountNumber);
        } catch (CardNotFoundException e) {

        }


        do {
            System.out.println();
            System.out.print("wlcome mr/ms " + AuthenticatedCustomer.logedInCustomer + "...\t");
            System.out.println("what do you wanna do? " +
                    " \n 1. " + cardService.cardExistance(accountNumber) + " card " +
                    " \n 2. transfer money" + "\n 3. go to the main menu" +
                    " \n 4. my card");
            System.out.println();
            System.out.print("key: ");
            int key = ZimaRed.scanner.nextInt();
            ZimaRed.scanner.nextLine();
            key10 = key;

            switch (key) {
                case 1:
                    try{
                        cardService.createCardForCustomer(accountNumber);
                        show.showTheCard(accountNumber);
                    }catch(ActivecardExistException e) {
                        show.showException("you already have an active card");
                    }
                    break;

                case 2:
                    System.out.println("enter the card number that you want transfer money to it...");
                    System.out.print("card number: ");
                    String destinationCardNumber = ZimaRed.scanner.nextLine();
                    System.out.println("now please enter the amount that you want to transfer...");
                    System.out.print("amount: ");
                    double transferAmount = ZimaRed.scanner.nextDouble();
                    try {
                        cardTransferService.transferWithCardNumber(destinationCardNumber, sourceCardNumber, transferAmount);
                        show.showTransaction(AuthenticatedCustomer.logedInCustomer, transferAmount, sourceCardNumber, destinationCardNumber, " ");
                    }catch(InsufficientBalanceException e) {
                        show.showException("not enough balance");
                    }catch(SenderIsAlsoReciverException e){
                        show.showException("You cannot transfer money from your bank account to the same source account");
                    }catch(BankAccountNotFoundException e) {
                        show.showException("bank account not found try another one...");
                        break;
                    }
                    break;

                case 3:
                    System.out.println("home Page...");
                    break;

                case 4:
                    show.showTheCard(accountNumber);
                    break;

                default:
                    System.out.println("plaese enter one of following numbers ");
                    break;

            }
        } while (key10 != 3);
    }
}
