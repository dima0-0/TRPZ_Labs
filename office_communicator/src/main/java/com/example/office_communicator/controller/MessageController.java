package com.example.office_communicator.controller;

import com.example.office_communicator.auth.entity.User;
import com.example.office_communicator.auth.repository.UserRepository;
import com.example.office_communicator.entity.Message;
import com.example.office_communicator.service.FileService;
import com.example.office_communicator.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
@CrossOrigin
public class MessageController {
    private final FileService fileService;
    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping(value = "/file-upload", consumes = {"multipart/form-data"})
    public Message uploadFileToServer(@RequestParam("file") MultipartFile file,
                                      @RequestParam("message") String message,
                                      @AuthenticationPrincipal UserDetails userDetails,
                                      @RequestParam("receiver") String receiver) {
        System.out.println(file);
        try {
            String fileUpload = fileService.saveFile(file);
            System.out.println("this is file : " + fileUpload);
            String sender = userRepository
                    .findByEmail(userDetails.getUsername()).map(User::getName).orElse(null);
            return messageService.sendMessage(new Message(sender, receiver, message, fileUpload));
        } catch (Exception e) {
            System.out.println("error message : " + e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/file-download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource downloadFileFromServer(@PathVariable String fileName) {
        System.out.println("file name : " + fileName);
        return fileService.downloadFile(fileName);
    }

    @PostMapping("/send-message")
    public Message sendMessage(@RequestBody Message message, @AuthenticationPrincipal UserDetails userDetails) {
        userRepository.findByEmail(userDetails.getUsername()).map(user -> {
            message.setSender(user.getName());
            return user;
        });
        System.out.println("message : " + message);
        return messageService.sendMessage(message);
    }

    @GetMapping("/{userName}")
    List<Message> messageList(@PathVariable String userName,
                              @AuthenticationPrincipal UserDetails userDetails) {
        String sender = userRepository
                .findByEmail(userDetails.getUsername()).map(User::getName).orElse(null);
        System.out.println("sender : " + sender + " receiver : " + userName + ".");
        return messageService.getMessageList(userName, sender);
    }
}
