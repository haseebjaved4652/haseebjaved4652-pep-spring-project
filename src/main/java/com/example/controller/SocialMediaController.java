package com.example.controller;

import java.util.List;

import org.springframework.*;

import com.example.*;
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

    

}