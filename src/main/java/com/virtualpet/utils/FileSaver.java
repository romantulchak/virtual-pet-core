package com.virtualpet.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileSaver {


    public static String saveFile(MultipartFile file, String fileUploadPath, String directory){
        if(file != null){
            File dir = new File(fileUploadPath + directory);
            if (!dir.exists()) {
                dir.mkdir();
            }
            try{
                String fileOriginalName = UUID.randomUUID() + "." + file.getOriginalFilename().replace(" ","-");
                String filePath = fileUploadPath + "/" + directory + "/" + fileOriginalName;
                Path copyLocation = Paths.get(filePath);
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                return "http://localhost:8080/" + "uploads/" + directory + "/" + fileOriginalName;

            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        throw new RuntimeException("File not found");
    }
}
