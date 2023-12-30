package com.example.office_communicator.service;

import com.example.office_communicator.entity.Message;
import com.example.office_communicator.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
    public List<Message> getMessageList(String sender, String receiver) {
        List<Message> bySenderAndReceiver = messageRepository.findBySenderAndReceiver(sender, receiver);
        bySenderAndReceiver.addAll(
                messageRepository.findBySenderAndReceiver(receiver, sender)
        );
        return bySenderAndReceiver.stream().sorted(Comparator.comparingInt(Message::getId)).toList();
    }
}
