package com.example.studentapp.batch;

import com.example.studentapp.entity.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentItemProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) throws Exception {
        student.setName(student.getName().toUpperCase());
        return student;
    }
}
