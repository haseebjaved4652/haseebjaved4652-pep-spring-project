package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    //@Modifying
    //@Transactional
    /* @Query("INSERT INTO Account(username,password) values (?,?)") 
    void newUserAccountRegistration(@Param("username") String username, @Param("password") String password);
    @Query("SELECT username FROM Account WHERE username=?")
    public String getUsernameString(Account account); */
    @Query("FROM Account WHERE username=:username")
    public Account searchAccountByUsername(@Param("username") String username);
    public Account searchAccountByUsernameAndPassword(String username, String passwrod);
    public Account searchAccountByAccountId(Integer accountId);

}