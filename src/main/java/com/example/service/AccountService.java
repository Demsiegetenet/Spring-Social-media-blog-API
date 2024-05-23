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

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){
                List<Account> accounts = accountRepository.findAll();
                for(Account acc:accounts){
                    if(account.getAccountId()!=acc.getAccountId()){
                        if(account.getUsername().length()>0 && account.getPassword().length()>=4){
                            Account registeredAccount = new Account();
                            registeredAccount.setUsername(account.getUsername());
                            registeredAccount.setPassword(account.getPassword());
                            
                            return accountRepository.save(registeredAccount);
                        }
                    }
                }
    return null;
    }

    public Account findByAccountId(int account_id){

        Optional<Account> optionalAccount = accountRepository.findById(account_id);
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }else{
            return null;
        }
    }

    public Account loginAccount(Account account){
        List<Account> accounts = accountRepository.findAll();
        for(Account acc:accounts){
            if(account.getUsername().equals(acc.getUsername()) && account.getPassword().equals(acc.getPassword())){
                   
                return acc;
            }
        }
           return null;
    }
}
