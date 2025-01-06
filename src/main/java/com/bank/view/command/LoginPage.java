package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.data.repository.CustomerRepository;
import com.bank.service.AuthenticateService;
import com.bank.service.exception.CustomerNotExistException;
import com.bank.service.impl.AuthenticateServiceImpl;

public class LoginPage {

    private final AuthenticateService authenticateService;

    public LoginPage() {
        this.authenticateService = new AuthenticateServiceImpl();
    }

    public void login(){

        CustomerRepository customerRepository = new CustomerRepository();

            System.out.print("\n enter your username: ");
            String userName = ZimaRed.scanner.nextLine();

            System.out.println(" now please enter your password: ");
            String password = ZimaRed.scanner.nextLine();

            try{
                authenticateService.login(userName, password);
                System.out.println("you have logined successfully");
            }catch(CustomerNotExistException e) {
                System.out.println("your username or password is wrong");
            }
    }
}
