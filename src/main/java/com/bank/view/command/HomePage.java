package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.service.BankAccountService;
import com.bank.service.CardService;
import com.bank.service.DepositService;
import com.bank.service.TransferService;
import com.bank.service.WithdrawService;
import com.bank.service.exception.BankAccountNotFoundException;
import com.bank.service.exception.InsufficientBalanceException;
import com.bank.service.exception.SenderIsAlsoReciverException;
import com.bank.service.impl.BankAccountServiceImpl;
import com.bank.service.impl.CardServiceImpl;
import com.bank.service.impl.DepositServiceImpl;
import com.bank.service.impl.TransferServiceImpl;
import com.bank.service.impl.WithdrawServiceImpl;
import com.bank.view.security.AuthenticatedCustomer;


public class HomePage {
    private final BankAccountService bankAccountService;
    private final WithdrawService withdrawService;
    private final DepositService depositService;
    private final TransferService transferService;
    private final Show show;
    private final CardService cardService;
    private final CardPage cardPage;


    public HomePage() {
        this.bankAccountService = new BankAccountServiceImpl();
        this.withdrawService = new WithdrawServiceImpl();
        this.depositService = new DepositServiceImpl();
        this.transferService = new TransferServiceImpl();
        this.show = new Show();
        this.cardService = new CardServiceImpl();
        this.cardPage = new CardPage();

    }

    public void showHomeMenu(){
        int key10 = 0;
        String accountNumber = bankAccountService.getAccountNumber(AuthenticatedCustomer.logedInCustomer);

        do{
            System.out.println();
            System.out.print("wlcome mr/ms " + AuthenticatedCustomer.logedInCustomer + "...\t" );
            System.out.println("what do you wanna do? " + 
            " \n 1. deposit" + " \n 2. withdraw" + "\n 3. Get Balance" + 
            " \n 4. transfer money" + "\n 5. go to the main menu" +
            " \n 6. go to card menu");
            System.out.println();
            System.out.print("key: ");
            int key = ZimaRed.scanner.nextInt();
            ZimaRed.scanner.nextLine();
            key10 = key;

            switch(key){
                case 1:
                    System.out.print("please enter the amount you want to deposit: ");
                    double depositAmount = ZimaRed.scanner.nextDouble();
                    depositService.deposit(accountNumber, depositAmount);
                    show.showTransaction(AuthenticatedCustomer.logedInCustomer, depositAmount, accountNumber, "1", "added to");
                    break;

                case 2:
                    System.out.print("please enter the amount you want to deposit: ");
                    double withdrawAmount = ZimaRed.scanner.nextDouble();
                    try {
                        withdrawService.withdraw(accountNumber, withdrawAmount);
                        show.showTransaction(AuthenticatedCustomer.logedInCustomer, withdrawAmount, accountNumber, "1", "gone from");
                    }catch(InsufficientBalanceException e){
                        show.showException("not enough balance");
                    }
                    break;

                case 3:
                    show.showBalance();
                    break;

                case 4:
                    System.out.println("enter the account number that you want transfer money to it...");
                    System.out.print("account number: ");
                    String destinationAccountNumber = ZimaRed.scanner.nextLine();
                    System.out.println("now please enter the amount that you want to transfer...");
                    System.out.print("amount: ");
                    double transferAmount = ZimaRed.scanner.nextDouble();
                    try {
                            transferService.transferWithBankAccount(destinationAccountNumber, accountNumber, transferAmount);
                            show.showTransaction(AuthenticatedCustomer.logedInCustomer, transferAmount, accountNumber, destinationAccountNumber, " ");
                    }catch(InsufficientBalanceException e) {
                        show.showException("not enough balance");
                    }catch(SenderIsAlsoReciverException e){
                        show.showException("You cannot transfer money from your bank account to the same source account");
                    }catch(BankAccountNotFoundException e) {
                        show.showException("bank account not found try another one...");
                        break;
                    }

                case 5:
                    System.out.println("main menu...");
                    break;

                case 6:
                    cardPage.showMenu();
                    break;

                default:
                    System.out.println("plaese enter one of following numbers ");
                    break;
            }

        }while(key10 != 5);
    }
    
}
