package com.mnzit.idor.poc.repository;

import com.mnzit.idor.poc.model.Student;

import java.util.List;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
public interface StudentRepository {

    List<Student> getAll();

    Student getById(Long id);

    void save(Student student);
}
