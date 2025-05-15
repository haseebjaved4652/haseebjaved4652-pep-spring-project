package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    AccountService accountService;

    public MessageService (MessageService messageRepository, AccountRepository accountRepository) {
        this.messageRepository= messageRepository;
        this.accountRepository = accountRepository;
    }


    public Message createNewMessageService
    

}