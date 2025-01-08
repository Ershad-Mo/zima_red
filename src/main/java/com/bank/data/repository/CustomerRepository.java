package com.bank.data.repository;
import java.util.ArrayList;

import java.util.List;

import com.bank.data.entity.Customer;

public class CustomerRepository {
    public static final List<Customer> customers = new ArrayList<>();

    public void save(Customer customer){
        customers.add(customer);
    }

    public boolean existByUsername(String userName){
        for(Customer customer : customers){
            if(customer.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean passwordValidation(String password){
        for(Customer customer : customers){
            if(customer.getPassWord().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean existByUsernameAndpassword(String userName, String password){
        for(Customer customer : customers){
            if(customer.getUserName().equals(userName) && customer.getPassWord().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean existByUsernameAndPassword(String username, String password) {
        return false;
    }
}
