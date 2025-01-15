package com.substring.foodie.service.impl;

import com.substring.foodie.dto.FileData;
import com.substring.foodie.exception.InvalidFilePathException;
import com.substring.foodie.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService implements FileService {


    private Logger log = LoggerFactory.getLogger(FileUploadService.class);


    @Override
    public FileData uploadFile(MultipartFile file, String path) throws IOException {

        if (path.isBlank()) {
            throw new InvalidFilePathException("Invalid upload path !!");
        }

//
//        uploads/abc.png

        Path folderPath = Paths.get(path.substring(0, path.lastIndexOf("/") + 1));
        log.info(folderPath.toString());

        //if folder not exists it will create
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);

        }


        String contentType = file.getContentType();
        if (contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {

        } else {
            throw new InvalidFilePathException("Invalid Content !!");
        }


        //file name

        String fileName = path.substring(path.lastIndexOf("/") + 1);

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")) {

        } else {
            throw new InvalidFilePathException("Invalid upload extension !!");
        }


        Path filePath = Paths.get(path);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


        FileData fileData = new FileData(fileName, path);

        return fileData;
    }

    @Override
    public void deleteFile(String path) {

    }

    @Override
    public Resource loadFile(String path) {
        return null;
    }


}
