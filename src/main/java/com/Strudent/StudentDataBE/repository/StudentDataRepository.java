package com.Strudent.StudentDataBE.repository;

import com.Strudent.StudentDataBE.models.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentDataRepository extends MongoRepository<Students,String> {}
