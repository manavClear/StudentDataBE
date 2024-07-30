package com.Strudent.StudentDataBE.service;

import com.Strudent.StudentDataBE.DTO.SuccessModalDTO;
import com.Strudent.StudentDataBE.models.Students;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentDataService {
    public ResponseEntity<String> uploadStudentData(MultipartFile file);

    public List<Students> getStudents();
}
