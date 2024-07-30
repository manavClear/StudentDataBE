package com.Strudent.StudentDataBE.controllers;

import com.Strudent.StudentDataBE.DTO.SuccessModalDTO;
import com.Strudent.StudentDataBE.models.Students;
import com.Strudent.StudentDataBE.service.StudentDataService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StrudentDataController {
    private final StudentDataService studentDataService;

    @PostMapping("/students/upload")
    public ResponseEntity<String> Upload(@RequestBody MultipartFile file){
        return studentDataService.uploadStudentData(file);
    }

    @GetMapping("/students")
    public List<Students> getStudents(){
        return studentDataService.getStudents();
    }
}
