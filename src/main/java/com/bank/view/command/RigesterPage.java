package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.data.entity.Customer;
import com.bank.service.CustomerService;
import com.bank.service.exception.CustomerExistException;
import com.bank.service.impl.CustomerServiceImpl;
import com.bank.view.security.AuthenticatedCustomer;

public class RigesterPage {

    private final CustomerService customerService;
    private final HomePage homePage;

    public RigesterPage() {
        this.customerService = new CustomerServiceImpl();
        this.homePage = new HomePage();
    }
    public void Add(){
        Customer customer = new Customer();
        System.out.print("\n please set a username for your account: ");
        String userName = ZimaRed.scanner.nextLine();

            if(userName.length() > 5){
                customer.setUserName(userName);
                // accountNum.accountNumber();
            }else
                    System.out.println("your username cannot be less than 5 letter");

        System.out.print("\n please set a password for your account: ");
        String password = ZimaRed.scanner.nextLine();

            if(password.length() > 5){

                System.out.print("\nrepeat your password: ");
                String repassword = ZimaRed.scanner.nextLine();

                if(repassword.equals(password)){
                    customer.setPassWord(password);

                    try{
                        customerService.addCustomer(customer);
                        System.out.println();
                        System.out.println("your account has been created successfully...");
                        AuthenticatedCustomer.logedInCustomer = userName;
                        homePage.showHomeMenu();
                    }catch(CustomerExistException e){
                        System.out.println("this username already exist, try another one...");
                    }

                }else
                System.out.println("wrong password, try again...");

            }else       
                System.out.println("PASSWORD MUST BE MORE THAN 5 NUMBER...");

    }
}

