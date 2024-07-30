package com.Strudent.StudentDataBE.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class SuccessModalDTO {
    private String id;
    private String message;
}
