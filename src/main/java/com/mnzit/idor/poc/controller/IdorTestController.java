package com.mnzit.idor.poc.controller;

import com.mnzit.idor.poc.converter.IsHash;
import com.mnzit.idor.poc.converter.Unhash;
import com.mnzit.idor.poc.model.Student;
import com.mnzit.idor.poc.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("poc/idor")
public class IdorTestController {

    private final StudentRepository studentRepository;

    @GetMapping("students")
    public List<Student> getAllStudent() {
        return studentRepository.getAll();
    }

    @GetMapping("students/{id}")
    public Student getStudent(@Unhash @IsHash @PathVariable("id") Long id) {
        return studentRepository.getById(id);
    }
}
