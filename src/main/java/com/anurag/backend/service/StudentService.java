package com.anurag.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.backend.entity.Student;
import com.anurag.backend.exception.StudentNotFoundException;
import com.anurag.backend.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student getStudentById(Long id) {
    return studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
}
public Student updateStudent(Long id, Student updatedStudent) {

    Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

    existingStudent.setName(updatedStudent.getName());
    existingStudent.setRollNo(updatedStudent.getRollNo());
    existingStudent.setEmail(updatedStudent.getEmail());
    existingStudent.setPhone(updatedStudent.getPhone());
    existingStudent.setCourse(updatedStudent.getCourse());
    existingStudent.setYear(updatedStudent.getYear());
    existingStudent.setRoomNo(updatedStudent.getRoomNo());

    return studentRepository.save(existingStudent);
}
public void deleteStudent(Long id) {

    if (!studentRepository.existsById(id)) {
        throw new StudentNotFoundException(id);
    }

    studentRepository.deleteById(id);
}

}