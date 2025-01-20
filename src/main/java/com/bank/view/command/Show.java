package com.bank.view.command;

import java.time.LocalDate;

import com.bank.service.BankAccountService;
import com.bank.service.impl.BankAccountServiceImpl;
import com.bank.view.security.AuthenticatedCustomer;

public class Show {
    private final BankAccountService bankAccountService;

    public Show() {
        this.bankAccountService = new BankAccountServiceImpl();
    }


    public void showBalance() {
        String accountNumber = bankAccountService.getAccountNumber(AuthenticatedCustomer.logedInCustomer);
        System.out.print("---------------------------\n");
        System.out.println("Your Account Number: " + accountNumber);
        System.out.print("---------------------------\n");
        System.out.print("your new balance is: " + bankAccountService.getCustomerbalance(AuthenticatedCustomer.logedInCustomer));
        System.out.println("\n---------------------------");
    }

    public void showException(String ExceptionInformations) {
        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        System.out.print( ExceptionInformations + "\n");
        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public void showTransaction(String username, double amount,
     String SourceAccountNumber, String distinationAccountNumber, String action){
        
        if(distinationAccountNumber == "1") {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("name: " + username);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("accountNumber: " + SourceAccountNumber);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(amount + "$ has been " + action + " your bank account");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("date: " + LocalDate.now());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }else{
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("name: " + username);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Source Account Number: " + SourceAccountNumber);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(amount + "$ has been sended to " + distinationAccountNumber);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("date: " + LocalDate.now());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

    }
}
