package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {

    @Autowired
   AccountService accountService;
   @Autowired
   MessageService messageService;

   @Autowired
   AccountRepository accountRepository;

   public SocialMediaController(AccountService accountService, AccountRepository accountRepository,MessageService messageService){
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.messageService = messageService;
   }

   @PostMapping("/register")
   public ResponseEntity<Account> registerAccount(@RequestBody Account account){
    Account registeredAccount = accountService.registerAccount(account);
    if(registeredAccount!=null)
    return ResponseEntity.status(200).body(registeredAccount);
    else
        return ResponseEntity.status(409).build();
   }

   @PostMapping("/login")
   public ResponseEntity<Account> loginAccount(@RequestBody Account account){
      Account loginAccount = accountService.loginAccount(account);
      if(loginAccount!=null)
      return ResponseEntity.status(200).body(loginAccount);
      else
      return ResponseEntity.status(401).build();
   }

   @GetMapping("/messages")
   public List<Message> getAllMessages(){
      return messageService.getAllMessages();
   }
}
