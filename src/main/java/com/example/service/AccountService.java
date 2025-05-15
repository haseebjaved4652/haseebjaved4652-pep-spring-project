package com.example.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
@Transactional
public class AccountService {
    AccountRepository accountRepository;
    Account account;
    AccountService accountService;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    
}