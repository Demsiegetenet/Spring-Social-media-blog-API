package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository; 
import com.example.repository.MessageRepository;

@Service
public class MessageService { 

   @Autowired
   MessageRepository messageRepository;

    @Autowired
    AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

     public Message addMessage(Message message) {
      return messageRepository.save(message);             
     }

     public Optional<Message> getMessageByMessageId(int message_id){
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isPresent()){
            return optionalMessage;
        }else{
            return null;
        }
        }

        public Message updateMesage(Message message,int message_id){
            List<Message> messages = messageRepository.findAll();
            for(int i=0;i<messages.size();i++){
                if(message_id==messages.get(i).getMessageId()){
                   if(message.getMessageText().length()>0 && message.getMessageText().length()<=255){
                        messages.get(i).setMessageText(message.getMessageText());                   
                        messageRepository.save(messages.get(i));
                        return messages.get(i);
                    }
                }
                }
            
            return null ;  
    }


    public List<Message> getMessageByPostedBy(int postedBy){

        List<Message> allMesages = messageRepository.findAll();
        List<Message> addedMessages = new ArrayList<>();

        for(Message msg: allMesages){
            if(postedBy==msg.getPostedBy()){
                addedMessages.add(msg);
            }
        }
    return addedMessages;
    }

    public void deleteMessageById(int messageId){
       messageRepository.deleteById(messageId);
    }
}