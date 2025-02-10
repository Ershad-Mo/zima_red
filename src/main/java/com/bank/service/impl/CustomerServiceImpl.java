package com.bank.service.impl;

import com.bank.data.entity.Customer;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerService;
import com.bank.service.exception.CustomerExistException;
import com.bank.data.databaserepository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BankAccountService bankAccountService;

    public CustomerServiceImpl() {
        this.customerRepository = new CustomerRepository();
        this.bankAccountService = new BankAccountServiceImpl();
    }

    @Override
    public void addCustomer(Customer customer) {
        if(customerRepository.existByUsername(customer.getUserName())) {
            throw new CustomerExistException();
        }
        customerRepository.save(customer);
        bankAccountService.createBankAccountForCustomer(customer.getUserName());
    }
}
