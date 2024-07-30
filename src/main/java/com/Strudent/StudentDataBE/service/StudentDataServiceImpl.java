package com.Strudent.StudentDataBE.service;


import com.Strudent.StudentDataBE.DTO.SuccessModalDTO;
import com.Strudent.StudentDataBE.models.Students;
import com.Strudent.StudentDataBE.repository.StudentDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentDataServiceImpl implements StudentDataService {
    private final StudentDataRepository studentDataRepository;

    @Override
    public List<Students> getStudents() {
        return studentDataRepository.findAll();
    }


    @Override
    public ResponseEntity<String> uploadStudentData(MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a file!", HttpStatus.BAD_REQUEST);
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Students> students = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                Cell decisionCell = row.getCell(9);
                if (decisionCell != null && decisionCell.getCellType() == CellType.STRING &&
                        !decisionCell.getStringCellValue().equalsIgnoreCase("No")) {

                    Students student = new Students();
                    student.setCompany(getStringCellValue(row.getCell(0)));
                    student.setYear(getStringCellValue(row.getCell(1)));
                    student.setEnrollmentNumber(getStringCellValue(row.getCell(3)));
                    student.setName(getStringCellValue(row.getCell(4)));
                    student.setDepartment(getStringCellValue(row.getCell(5)));
                    student.setDegree(getStringCellValue(row.getCell(6)));
                    student.setContactNumber(getStringCellValue(row.getCell(7)));
                    student.setEmail(getStringCellValue(row.getCell(8)));

                    students.add(student);
                }
            }

            studentDataRepository.saveAll(students);
            return new ResponseEntity<>("File uploaded and data saved successfully!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("An error occurred while processing the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
