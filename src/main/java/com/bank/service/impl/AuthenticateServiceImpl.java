package com.bank.service.impl;

import com.bank.data.repository.CustomerRepository;
import com.bank.service.AuthenticateService;
import com.bank.service.exception.CustomerNotExistException;

public class AuthenticateServiceImpl implements AuthenticateService {

        private final CustomerRepository customerRepository;

        public AuthenticateServiceImpl() {
            this.customerRepository = new CustomerRepository();
        }
        @Override
        public void login(String username, String password) {
            if(!customerRepository.existByUsernameAndPassword(username, password)) {
                throw new CustomerNotExistException();
        }
    }
}
