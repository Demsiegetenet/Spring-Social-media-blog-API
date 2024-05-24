package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Controller
public class SocialMediaController {

    @Autowired
   AccountService accountService;

   @Autowired
   MessageService messageService;


   public SocialMediaController(AccountService accountService,MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
   }

   @PostMapping("/register")
   public ResponseEntity<Account> registerAccount(@RequestBody Account account){
         List<Account> accounts = accountService.getAllAccounts();
      
         for(Account acc:accounts){
               if(account.getUsername().equals(acc.getUsername()))
                  return ResponseEntity.status(409).build();
               else{
                  if(account.getUsername().length()>0 && account.getPassword().length()>=4){
                     Account registeredAccount = accountService.registerAccount(account);
                     return ResponseEntity.status(200).body(registeredAccount);
                  }
               }
         }
   return ResponseEntity.status(400).build();
   }

   @PostMapping("/login")
   public ResponseEntity<Account> loginAccount(@RequestBody Account account){
      Account loginAccount = accountService.loginAccount(account);
      if(loginAccount!=null)
         return ResponseEntity.status(200).body(loginAccount);
      else
         return ResponseEntity.status(401).build();
   }

   @PostMapping("/messages")
   public ResponseEntity<Message> createMessgae(@RequestBody Message message){
       Message addedMessage = messageService.addMessage(message);
       if(addedMessage!=null)
          return ResponseEntity.status(200).body(addedMessage);
       else
          return ResponseEntity.status(409).body(addedMessage);

   }

   @GetMapping("/messages")
   public ResponseEntity<List<Message>> getAllMessages(){
      return ResponseEntity.status(200).body(messageService.getAllMessages());
   }

   @GetMapping("/messages/{message_id}")
   public ResponseEntity<Optional<Message>> getMessageById(@PathVariable int message_id){
      if(messageService.getMessageByMessageId(message_id)!=null)
      return ResponseEntity.status(200).body(messageService.getMessageByMessageId(message_id));
      else
      return ResponseEntity.status(200).build();
   }

   @PatchMapping("/messages/{message_id}")
   public ResponseEntity<Integer> updateMessage(@RequestBody Message message, @PathVariable int message_id){
      Message updatedMessage = messageService.updateMesage(message,message_id);
      if(updatedMessage!=null)
          return ResponseEntity.status(200).body(1);
      else
          return ResponseEntity.status(400).build();
   }
}
