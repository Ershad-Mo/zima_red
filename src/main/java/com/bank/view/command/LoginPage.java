package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.data.repository.CustomerRepository;
import com.bank.service.AuthenticateService;
import com.bank.service.exception.CustomerNotExistException;
import com.bank.service.impl.AuthenticateServiceImpl;

public class LoginPage {

    private final AuthenticateService authenticateService;
    int key10 = 0;

    public LoginPage() {
        this.authenticateService = new AuthenticateServiceImpl();
    }

    public void login(){
        CustomerRepository customerRepository = new CustomerRepository();

            System.out.print("\n enter your username: ");
            String userName = ZimaRed.scanner.nextLine();

            System.out.print(" now please enter your password: ");
            String password = ZimaRed.scanner.nextLine();

            try{
                authenticateService.login(userName, password);
                System.out.println("you have logined successfully");

                do{
                System.out.println("wlcome mr/ms " + userName + "..." );
                System.out.println("what do you wanna do? " + 
                " \n 1. deposit" + " \n 2. withdraw" + 
                " \n 3.balance " + "\n 4. go to the main menu");
                System.out.print("key: ");
                int key = ZimaRed.scanner.nextInt();
                ZimaRed.scanner.nextLine();
                key10 = key;

                if(key > 4 || key < 1)
                    System.out.println("plaese enter one of following numbers ");

            }while(key10 != 4);
            }catch(CustomerNotExistException e) {
                System.out.println("your username or password is wrong");
            }
    }
}
