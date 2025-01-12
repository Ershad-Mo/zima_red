package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.service.AuthenticateService;
import com.bank.service.exception.CustomerNotExistException;
import com.bank.service.impl.AuthenticateServiceImpl;
import com.bank.view.security.AuthenticatedCustomer;

public class LoginPage {

    private final AuthenticateService authenticateService;
    private final HomePage homePage;
    int key10 = 0;

    public LoginPage() {
        this.authenticateService = new AuthenticateServiceImpl();
        this.homePage = new HomePage();
    }

    public void login(){
            System.out.print("\n enter your username: ");
            String userName = ZimaRed.scanner.nextLine();
            System.out.println();

            System.out.print(" now please enter your password: ");
            String password = ZimaRed.scanner.nextLine();

            try{
                authenticateService.login(userName, password);
                System.out.println();
                System.out.println("you have logined successfully");
                AuthenticatedCustomer.logedInCustomer = userName;
                homePage.showHomeMenu();

            }catch(CustomerNotExistException e) {
                System.out.println("your username or password is wrong");
            }
    }
}
