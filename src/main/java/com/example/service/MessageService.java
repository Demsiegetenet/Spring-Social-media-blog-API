package com.example.service;

import java.util.List;

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

    public Message addMessage(Message newMessage){
       List<Message> messages = messageRepository.findAll();
       for(Message msg:messages){
        if(newMessage.getPostedBy()!=msg.getPostedBy()){
            if(newMessage.getMessageText().length()>0&&newMessage.getMessageText().length()<255){
                Message message = new Message();
                        message.setMessageText(newMessage.getMessageText());
                        message.setPostedBy(newMessage.getPostedBy());
                        message.setTimePostedEpoch(newMessage.getTimePostedEpoch());
                return messageRepository.save(message);
            }
        }
       }
       return null;
    }

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
}
