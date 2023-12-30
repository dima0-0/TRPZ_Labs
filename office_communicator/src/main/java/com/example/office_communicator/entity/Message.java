package com.example.office_communicator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String sender;
    String receiver;
    String message;
    String filePath;


    public Message(String fromUser, String toUser, String message) {
        this.sender = fromUser;
        this.receiver = toUser;
        this.message = message;
    }

    public Message(String fromUser, String toUser, String message, String file) {
        this.sender = fromUser;
        this.receiver = toUser;
        this.message = message;
        this.filePath = file;
    }
}
