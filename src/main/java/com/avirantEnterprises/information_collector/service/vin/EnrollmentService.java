package com.avirantEnterprises.information_collector.service.vin;

import com.avirantEnterprises.information_collector.model.vin.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Student student, MultipartFile idDocument);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    byte[] getDocumentContent(String documentPath); // Method to return file content
}
