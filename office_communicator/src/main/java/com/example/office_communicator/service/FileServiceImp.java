package com.example.office_communicator.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {

    Path path;

    public FileServiceImp() {
        path = Paths.get("src/main/resources/images");
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        fileName = uuid + fileName;

        Path resolvePath = path;
        if (!file.isEmpty()) {
            resolvePath = path.resolve(fileName);
        }
        Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @Override
    public FileSystemResource downloadFile(String fileName) {
        try {
            FileSystemResource fileSystemResource = new FileSystemResource(path.resolve(fileName));
            System.out.println("File name: "+fileSystemResource.getFilename());
            return fileSystemResource;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}