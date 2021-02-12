package com.mnzit.idor.poc.repository.impl;

import com.mnzit.idor.poc.model.Student;
import com.mnzit.idor.poc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
@Repository
public class StudentRepositoryMemoryImpl implements StudentRepository {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1L, "Manjit"));
        students.add(new Student(2L, "Diwash"));
        students.add(new Student(3L, "Pratistha"));
    }

    @Override
    public List<Student> getAll() {
        log.debug("Fetching all students");
        return students;
    }

    @Override
    public void save(Student student) {
        log.debug("Saving student : {}", student.toString());
        students.add(student);
    }

    @Override
    public Student getById(Long id) {
        log.debug("Fetching student with id : {}", id);
        return students
                .stream()
                .filter(student -> student.getId().compareTo(id) == 0)
                .findFirst()
                .orElse(null);
    }
}
