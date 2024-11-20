package com.avirantEnterprises.information_collector.controller.vin;

import com.avirantEnterprises.information_collector.service.vin.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @GetMapping("/certification-upload")
    public String showCertificationUploadForm() {
        return "certification-upload"; // The HTML template created earlier
    }

    @GetMapping("/certifications-view")
    public String viewUploadedCertifications(Model model) {
        // Fetch the list of certifications from the service
        model.addAttribute("certifications", certificationService.getAllCertifications());
        return "certifications-list"; // The HTML template created above
    }
    @PostMapping("/certification-upload")
    public String handleCertificationUpload(
            @RequestParam(value = "courseTitle", required = false) String courseTitle,
            @RequestParam(value = "issuingOrganization", required = false) String issuingOrganization,
            @RequestParam(value = "completionDate", required = false) String completionDate,
            @RequestParam(value = "certificationNumber", required = false) String certificationNumber,
            @RequestParam("file") MultipartFile file,
            Model model) {

        try {
            // Parse the completion date only if it's provided
            LocalDate parsedCompletionDate = (completionDate != null && !completionDate.isEmpty())
                    ? LocalDate.parse(completionDate)
                    : null;

            // Save the certification using the service
            certificationService.saveCertification(
                    courseTitle,
                    issuingOrganization,
                    parsedCompletionDate,
                    certificationNumber,
                    file
            );

            model.addAttribute("message", "Certification uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "Error uploading certification.");
            e.printStackTrace();
        }

        return "certification-upload";  // Redirect to success page
    }
}
