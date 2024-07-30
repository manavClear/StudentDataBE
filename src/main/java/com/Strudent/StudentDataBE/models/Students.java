package com.Strudent.StudentDataBE.models;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Slf4j
@Data
public class Students {
    @Id
    @Field("id")
    private String id;
    private String name;
    private String enrollmentNumber;
    private String company;
    private String email;
    private String department;
    private String degree;
    private String contactNumber;
    private String year;
}
