package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.*;
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account registerAccount(Account account){
    List<Account> accounts = accountRepository.findAll();
   
    if(account.getUsername().length()>0 && account.getPassword().length()>=4){
        for(Account acc:accounts){
            if(acc.getAccountId()!=account.getAccountId()){
                accountRepository.save(account);
                return account;
            }
            else
            return null;
        }
    }
   
     return null;
    }
}
