package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.service.BankAccountService;
import com.bank.service.DepositService;
import com.bank.service.WithdrawService;
import com.bank.service.exception.InsufficientBalanceException;
import com.bank.service.impl.BankAccountServiceImpl;
import com.bank.service.impl.DepositServiceImpl;
import com.bank.service.impl.WithdrawServiceImpl;
import com.bank.view.security.AuthenticatedCustomer;


public class HomePage {
    private final BankAccountService bankAccountService;
    private final WithdrawService withdrawService;
    private final DepositService depositService;

    public HomePage() {
        this.withdrawService = new WithdrawServiceImpl();
        this.depositService = new DepositServiceImpl();
        this.bankAccountService = new BankAccountServiceImpl();

    }

    public void showHomeMenu(){
        int key10 = 0;

        do{
            System.out.println();
            System.out.print("wlcome mr/ms " + AuthenticatedCustomer.logedInCustomer + "...\t" );
            System.out.println("what do you wanna do? " + 
            " \n 1. deposit" + " \n 2. withdraw" + 
            " \n 3. balance " + "\n 4. go to the main menu");
            System.out.println();
            System.out.print("key: ");
            int key = ZimaRed.scanner.nextInt();
            ZimaRed.scanner.nextLine();
            key10 = key;

            switch(key){
                case 1:
                    System.out.print("please enter the amount you want to deposit: ");
                    double amount = ZimaRed.scanner.nextDouble();
                    depositService.deposit(bankAccountService.getAccountNumber(AuthenticatedCustomer.logedInCustomer), amount);
                    System.out.print("----------------------\n");
                    System.out.print("your new balance is: " + bankAccountService.getCustomerbalance(AuthenticatedCustomer.logedInCustomer));
                    System.out.println("\n----------------------");
                break;

                case 2:
                System.out.print("please enter the amount you want to deposit: ");
                double amount1 = ZimaRed.scanner.nextDouble();
                try {
                    withdrawService.withdraw(bankAccountService.getAccountNumber(AuthenticatedCustomer.logedInCustomer), amount1);
                    System.out.print("----------------------\n");
                    System.out.print("your new balance is: " + bankAccountService.getCustomerbalance(AuthenticatedCustomer.logedInCustomer));
                    System.out.println("\n----------------------");
                }catch(InsufficientBalanceException e){
                    System.out.println("not enough balance");
                }
                    
                break;

                case 3:
                    System.out.print("----------------------\n");
                    System.out.print("your balance is: " + bankAccountService.getCustomerbalance(AuthenticatedCustomer.logedInCustomer) );
                    System.out.println("\n----------------------");
                break;

                case 4:
                System.out.println("main menu...");
                break;

                default:
                    System.out.println("plaese enter one of following numbers ");
                    break;

            }

        }while(key10 != 4);
    }
    
}
