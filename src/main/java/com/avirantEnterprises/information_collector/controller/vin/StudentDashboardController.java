package com.avirantEnterprises.information_collector.controller.vin;

import com.avirantEnterprises.information_collector.model.vin.Student;
import com.avirantEnterprises.information_collector.service.vin.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StudentDashboardController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student-dashboard";
    }

    @PostMapping("/student/upload")
    public String uploadStudentData(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("courseName") String courseName,
            @RequestParam("dateOfBirth") String dateOfBirth,  // Receive as string
            @RequestParam("idDocument") MultipartFile idDocument) throws IOException {

        // Parse the dateOfBirth string into a Date object
        Date parsedDateOfBirth = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            parsedDateOfBirth = sdf.parse(dateOfBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new Student object and set the fields
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setCourseName(courseName);
        student.setDateOfBirth(parsedDateOfBirth);  // Set the parsed date

        // Pass the Student object and document to the service
        enrollmentService.enrollStudent(student, idDocument);

        return "enrollSuccess";  // Return the success page
    }
}
