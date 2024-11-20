package com.avirantEnterprises.information_collector.service.vin;

import com.avirantEnterprises.information_collector.model.vin.Student;
import com.avirantEnterprises.information_collector.repository.vin.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public void enrollStudent(Student student, MultipartFile idDocument) {
        try {
            Files.createDirectories(rootLocation);
            Path destinationFile = rootLocation.resolve(idDocument.getOriginalFilename())
                    .normalize().toAbsolutePath();
            idDocument.transferTo(destinationFile);
            student.setDocumentPath(destinationFile.toString());
            studentRepository.save(student);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public byte[] getDocumentContent(String documentPath) {
        try {
            Path filePath = Paths.get(documentPath);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content.", e);
        }
    }
}
