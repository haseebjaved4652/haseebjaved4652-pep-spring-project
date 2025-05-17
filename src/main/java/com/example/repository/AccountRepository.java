package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("FROM Account WHERE username=:username")

    public Account searchAccountByUsername(@Param("username") String username);
    public Account searchAccountByUsernameAndPassword(String username, String passwrod);
    public Account searchAccountByAccountId(Integer accountId);

}