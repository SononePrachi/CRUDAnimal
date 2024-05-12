package com.animal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Filename contains invalid path sequence: " + fileName);
            }

            // Generate a unique file name
            String newFileName = UUID.randomUUID().toString() + "_" + fileName;

            // Copy file to the target location (replace existing file with the same name)
            Path targetLocation = Paths.get(uploadDir).resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation);

            return newFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + fileName, ex);
        }
    }
}
