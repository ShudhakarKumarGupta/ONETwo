package com.upload.file.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.file.entity.FileEntity;
import com.upload.file.repository.FilessRepository;

import java.util.Optional;

@Service
public class FileService {

	@Autowired(required = true)   
	private FilessRepository fileRepository;

    public FileEntity uploadFile(MultipartFile file) throws Exception {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileData(file.getBytes());
        return fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> downloadFile(Long fileId) {
        return fileRepository.findById(fileId);
    }

    public Iterable<FileEntity> listAllFiles() {
        return fileRepository.findAll();
    }
}