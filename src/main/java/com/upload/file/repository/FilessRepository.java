package com.upload.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.file.entity.FileEntity;
@Repository
public interface FilessRepository extends JpaRepository<FileEntity, Long>{

}
