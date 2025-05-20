package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
@RequestMapping
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }


    /* 1 -> API can process new User Registration*/ 
    @PostMapping("/register") // endpoint POST localhost:8080/register
    public ResponseEntity<Account> register(@RequestBody Account model) {
        Account account = null;
        if (!model.getUsername().isEmpty() && model.getPassword().length() > 4) {
            account = accountService.registerAccount(model);
            if (account != null) {
                return ResponseEntity.status(200).body(account);
            } else
                return ResponseEntity.status(409).body(null); // not successful due to a duplicate username
        }
        return ResponseEntity.status(400).body(null); // not successful for some other reason
    }
    
    /* 2 -> API can process User Logins*/
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account model) {
        Account account = null;
        account = accountService.login(model.getUsername(), model.getPassword());
        if (account != null) {
            return ResponseEntity.status(200).body(account); // login will be successful. complete match
        } else
            return ResponseEntity.status(401).body(null); // login is not successful
    }


    /* 3 -> API can process creations of new messages*/
    @PostMapping("/messages")
    public ResponseEntity<Message> messages(@RequestBody Message model) {
        if(!model.getMessageText().isEmpty() && model.getMessageText().length() < 255) {
            Account account = accountService.getAccount(model.getPostedBy());
            if(account != null) {
                Message message = messageService.createMessage(model);
                if(message != null) {
                    return ResponseEntity.status(200).body(message);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



    /* 4 -> API can retrieve all messages*/
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> messages() {
        return ResponseEntity.ok(messageService.getMessages()); // response status should always be 200 aka (ok)
    }

    /* 5 -> API can retrieve a message given it has its ID */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> message(@PathVariable int messageId) {
        Optional<Message> message = messageService.getMessage(messageId);
        if(message.isPresent())
            return ResponseEntity.ok(message.get()); // response status should always be 200 aka (ok)
        return ResponseEntity.ok(null);
    }

    /* 6 -> API can delete a message given it has its message ID*/
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {
        Optional<Message> message = messageService.getMessage(messageId);
        if(message.isPresent()) {
            messageService.deleteMessage(messageId);
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.ok(null);
    }

    /* 7 -> API can update a message text given it has its message ID*/
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestBody Message model) {
        if(!model.getMessageText().isEmpty() && model.getMessageText().length() < 255) {
            Optional<Message> message = messageService.getMessage(messageId);
            if(message.isPresent()) {
                message.get().setMessageText(model.getMessageText());
                messageService.updateMessage(message.get());
                return ResponseEntity.ok(1);
            }
        }
        return ResponseEntity.status(400).body(null);
    }

    /* 8 -> API can retrieve all messages writeen by a particular user*/
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable int accountId) {
        Account account = accountService.getAccount(accountId);
        if(account != null) {
            List<Message> messages = messageService.getMessageByAccount(accountId);
            return ResponseEntity.ok(messages);
        }
        return ResponseEntity.status(200).body(null);
    }

}