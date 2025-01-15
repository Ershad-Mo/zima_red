package com.bank.view.command;

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
    
}
