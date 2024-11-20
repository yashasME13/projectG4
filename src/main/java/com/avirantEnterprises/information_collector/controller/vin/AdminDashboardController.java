package com.avirantEnterprises.information_collector.controller.vin;

import com.avirantEnterprises.information_collector.model.vin.Certification;
import com.avirantEnterprises.information_collector.model.vin.Student;
import com.avirantEnterprises.information_collector.service.vin.CertificationService;
import com.avirantEnterprises.information_collector.service.vin.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminDashboardController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CertificationService certificationService;  // Inject CertificationService

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("students", enrollmentService.getAllStudents());
        return "admin-dashboard";
    }
    @GetMapping("/admin/dashboard_two")
    public String adminDashboardTwo(Model model) {
        model.addAttribute("certifications", certificationService.getAllCertifications());
        return "admin_dashboard_two";
    }

    @GetMapping("/s/dashboard")
    public String sDashboard(Model model) {
        model.addAttribute("students", enrollmentService.getAllStudents());
        return "studentlogin";
    }

    @GetMapping("/admin/view/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Student student = enrollmentService.getStudentById(id);
        model.addAttribute("student", student);
        return "view-student"; // Create a view for detailed student information
    }

    @GetMapping("/admin/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        Student student = enrollmentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            byte[] fileContent = enrollmentService.getDocumentContent(student.getDocumentPath());
            if (fileContent == null) {
                return ResponseEntity.notFound().build();
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + student.getDocumentPath() + "\"");
                headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf"); // Set the correct mime type for the file
                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
            }
        }
    }

    // View all certifications
    @GetMapping("/admin/certifications")
    public String viewCertifications(Model model) {
        model.addAttribute("certifications", certificationService.getAllCertifications());
        return "certifications-list"; // View to display certifications
    }

    // View a specific certification
    @GetMapping("/admin/view-certification/{id}")
    public String viewCertification(@PathVariable Long id, Model model) {
        Certification certification = certificationService.getCertificationById(id);
        model.addAttribute("certification", certification);
        return "view-certification"; // View to show certification details
    }

    // Download a specific certification document
    @GetMapping("/admin/download-certification/{id}")
    public ResponseEntity<byte[]> downloadCertification(@PathVariable Long id) {
        Certification certification = certificationService.getCertificationById(id);
        if (certification == null) {
            return ResponseEntity.notFound().build();
        } else {
            byte[] fileContent = certification.getFileContent();
            if (fileContent == null) {
                return ResponseEntity.notFound().build();
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + certification.getFileName() + "\"");
                headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf"); // Correct mime type for the file
                return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
            }
        }
    }

//    @GetMapping("/certifications-view")
//    public String viewUploadedCertifications(Model model) {
//        // Fetch the list of certifications from the service
//        model.addAttribute("certifications", certificationService.getAllCertifications());
//        return "certifications-list"; // The HTML template created above
//    }
}