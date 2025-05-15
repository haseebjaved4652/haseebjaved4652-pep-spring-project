package com.example.controller;

import java.util.List;

import org.springframework.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.*;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
import com.example.service.AccountService;
import com.example.service.MessageService;
public class SocialMediaController {

    private AccountService accountService;
    private MessageService MessageService;
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    public SocialMediaController(AccountService accountService, MessageService MessageService) {
        this.MessageService = MessageService;
        this.accountService = accountService;
    }

    /* 1 -> API can process new User Registration*/ 
    @PostMapping("/Register")
    public ResponseEntity<Object> newUserAccountRegister(@RequestBody Account account) {
        Account registeredAccount = accountService.getAccountById(account.getUsername());
        if (registeredAccount != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /* 2 -> API can process User Logins*/
    /* 3 -> API can process creations of new messages*/
    /* 4 -> API can retrieve all messages*/
    /* 5 -> API can retrieve a message given it has its ID */
    /* 6 -> API can delete a message given it has its message ID*/
    /* 7 -> API can update a message text given it has its message ID*/
    /* 8 -> API can retrieve all messages writeen by a particular user*/
    

}