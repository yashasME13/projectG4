package com.avirantEnterprises.information_collector.service.vin;

import com.avirantEnterprises.information_collector.model.vin.Certification;
import com.avirantEnterprises.information_collector.repository.vin.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    // Method to save a new certification
    public void saveCertification(String courseTitle, String issuingOrganization, LocalDate completionDate, String certificationNumber, MultipartFile file) throws IOException {
        Certification certification = new Certification();
        certification.setCourseTitle(courseTitle);
        certification.setIssuingOrganization(issuingOrganization);
        certification.setCompletionDate(completionDate);
        certification.setCertificationNumber(certificationNumber);
        certification.setFileName(file.getOriginalFilename());
        certification.setFileContent(file.getBytes());

        certificationRepository.save(certification);
    }

    // Method to get all certifications
    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    // Method to get a specific certification by ID
    public Certification getCertificationById(Long id) {
        Optional<Certification> certificationOptional = certificationRepository.findById(id);
        return certificationOptional.orElse(null);  // Returns the certification if found, otherwise null
    }
}
