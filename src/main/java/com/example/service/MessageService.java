package com.example.service;

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

 

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // public Message addMessage(Message newMessage){
    //     List<Message> messages = messageRepository.findAll();
    //     for(Message msg:messages){
    //      if(newMessage.getPostedBy()!=msg.getPostedBy()){
    //          if(newMessage.getMessageText().length()>0&&newMessage.getMessageText().length()<255){
    //              Message message = new Message();
    //                      message.setMessageText(newMessage.getMessageText());
    //                      message.setPostedBy(newMessage.getPostedBy());
    //                      message.setTimePostedEpoch(newMessage.getTimePostedEpoch());
    //              return messageRepository.save(message);
    //          }
    //      }
    //     }
    //     return null;
    //  }

     public Message addMessage(Message message) {
        List<Account> accounts = accountRepository.findAll();
         for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccountId()==message.getPostedBy()){
                if(messageRepository.findById(message.getMessageId())==null){
                    if(message.getMessageText().length()>0 && message.getMessageText().length()<255){
                        Message messg = new Message();
                                              messg.setMessageText(message.getMessageText());
                                              messg.setPostedBy(message.getPostedBy());
                                              messg.setTimePostedEpoch(message.getTimePostedEpoch());
                                      return messageRepository.save(messg);
                 }
            } 
            }
         }
    return null;
}

     public void deleteMessageById(){

     }

     public Optional<Message> getMessageByPostedBy(int posted_by){
        return messageRepository.findById(posted_by);
        }

        public Message updateMesage(int message_id, Message message){
            List<Message> messages = messageRepository.findAll();
            for(int i=0;i<messages.size();i++){
                if(message_id==messages.get(i).getMessageId()){
                if(message.getMessageText().length()>0 && message.getMessageText().length()<=255){
                    Message m = new Message();
                        
                         m.setMessageText(message.getMessageText());
                         m.setPostedBy(message.getPostedBy());
                         m.setTimePostedEpoch(message.getTimePostedEpoch());
                         return messageRepository.save(m);

                    }
                }
                }
            
            return null ;  
    }

}