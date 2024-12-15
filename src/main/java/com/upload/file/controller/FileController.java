package com.upload.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.upload.file.entity.FileEntity;
import com.upload.file.service.FileService;

import java.util.Optional;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("files", fileService.listAllFiles());
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try {
            fileService.uploadFile(file);
            model.addAttribute("message", "File uploaded successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error uploading file.");
        }
        model.addAttribute("files", fileService.listAllFiles());
        return "index";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<FileEntity> fileEntityOptional = fileService.downloadFile(id);
        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(fileEntity.getFileData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}