package com.animal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Value("${file.upload-dir}")
    private String uploadDir;

    private Path fileStorageLocation;

    @PostConstruct
    public void init() {
        try {
            fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("Org file name = " + fileName);

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Filename contains invalid path sequence: " + fileName);
            }

            // Generate a unique file name
            String newFileName = UUID.randomUUID().toString() + "_" + fileName;
            System.out.println("newFilename = " + newFileName);

            // Copy file to the target location (replace existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);

            // Ensure the directory exists
            if (!Files.exists(targetLocation.getParent())) {
                Files.createDirectories(targetLocation.getParent());
            }

            Files.copy(file.getInputStream(), targetLocation);
            System.out.println("After Copy");
            return newFileName;
        } catch (IOException ex) {
            System.out.println("In catch = " + ex);
            throw new RuntimeException("Failed to store file " + fileName, ex);
        }
    }
}
