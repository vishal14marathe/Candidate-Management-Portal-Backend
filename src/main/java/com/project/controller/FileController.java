package com.project.controller;

import com.project.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/candidates")
public class FileController {
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/upload-resume/{id}")
    public ResponseEntity<String> uploadResume(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file, id);
        return ResponseEntity.ok("Resume uploaded successfully: " + fileName);
    }
    
    @GetMapping("/download-resume/{fileName}")
    public ResponseEntity<Resource> downloadResume(@PathVariable String fileName) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
