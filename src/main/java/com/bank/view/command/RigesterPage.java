package com.bank.view.command;

import com.bank.ZimaRed;
import com.bank.data.entity.Customer;
import com.bank.data.repository.CustomerRepository;
import com.bank.service.CustomerService;
import com.bank.service.exception.CustomerExistException;
import com.bank.service.impl.CustomerServiceImpl;

public class RigesterPage {

    private final CustomerService customerService;

    public RigesterPage() {
        this.customerService = new CustomerServiceImpl();
    }
    public void Add(){
        String passWord = "";
        
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

                    customer.setPassWord(passWord);
                    try{
                        customerService.addCustomer(customer);
                        System.out.println("your account has been created successfully...");
                    }catch(CustomerExistException e){
                        System.out.println("this username already exist, try another one...");
                    }

                }else
                System.out.println("wrong password, try again...");

            }else       
                System.out.println("PASSWORD MUST BE MORE THAN 5 NUMBER...");

    }
}

