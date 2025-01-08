package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.data.entity.Customer;
import com.bank.data.repository.CustomerRepository;
import com.bank.service.CustomerService;
import com.bank.service.exception.CustomerExistException;
import com.bank.service.impl.CustomerServiceImpl;

public class RigesterPage {

    private final CustomerService customerService;
    int key10 = 0;

    public RigesterPage() {
        this.customerService = new CustomerServiceImpl();
    }
    public void Add(){
        Customer customer = new Customer();
        CustomerRepository customerRepository = new CustomerRepository();
        System.out.print("\n please set a user name for your account: ");
        String userName = ZimaRed.scanner.nextLine();

            if(userName.length() > 5)
                    customer.setUserName(userName);
                else
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
                        System.out.println("your account has been created successfully...");
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
                    }catch(CustomerExistException e){
                        System.out.println("this username already exist, try another one...");
                    }

                }else
                System.out.println("wrong password, try again...");

            }else       
                System.out.println("PASSWORD MUST BE MORE THAN 5 NUMBER...");

    }
}

