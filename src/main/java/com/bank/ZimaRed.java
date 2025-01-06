package com.bank;
import java.util.Scanner;

import com.bank.data.repository.CustomerRepository;
import com.bank.view.command.WelcomePage;


public class ZimaRed {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        WelcomePage menu = new WelcomePage();
        menu.Key();

        CustomerRepository customer = new CustomerRepository();

        System.out.println(customer.customers);

        // CardInfo info = new CardInfo();
        // info.card();
         
        // AccountForCustomer ac = new AccountForCustomer();
        // System.out.println(ac.AccountNumber());
    }
}