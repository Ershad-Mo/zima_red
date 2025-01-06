package com.bank.service.impl;

import com.bank.data.entity.Customer;
import com.bank.service.CustomerService;
import com.bank.service.exception.CustomerExistException;
import com.bank.data.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl() {
        this.customerRepository = new CustomerRepository();
    }

    @Override
    public void addCustomer(Customer customer) {
        if(customerRepository.existByUsername(customer.getUserName())) {
            throw new CustomerExistException();
        }
        customerRepository.save(customer);
    }
}
