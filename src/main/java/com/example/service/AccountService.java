package com.example.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@Service
@Transactional
public class AccountService {
    AccountRepository accountRepository;
    Account account;
    AccountService accountService;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /*1- > API processes new User Registration
    
    As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a representation of a JSON Account, but will not contain an account_id.

    - The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist. If all these conditions are met, the response body should contain a JSON of the Account, including its account_id. The response status should be 200 OK, which is the default. The new account should be persisted to the database.
    - If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
    - If the registration is not successful for some other reason, the response status should be 400. (Client error)*/
   
    public Account getaccountByUsername(String username){
        return accountRepository.getAccountByUsername(username);
    }
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    /* 2 -> API caan process User Logins
        
    As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account, not containing an account_id. In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now.

    - The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. If successful, the response body should contain a JSON of the account in the response body, including its account_id. The response status should be 200 OK, which is the default.
    - If the login is not successful, the response status should be 401. (Unauthorized) */

    public String getUserNameString(Account account2){
        return account2.getUsername();
    }
    public Account getAccountById(int loginId){
        return accountRepository.getAccountById(loginId);
    }

    public Account checkAccount(String username, String password) {
        Account account = getAccountByUsername(username);
        if(account.getPassword().contains(password)){
            return account;
        }
        return null;
    }
}