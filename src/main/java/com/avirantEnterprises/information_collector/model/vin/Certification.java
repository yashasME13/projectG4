package com.avirantEnterprises.information_collector.model.vin;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseTitle;
    private String issuingOrganization;
    private LocalDate completionDate;
    private String certificationNumber;

    // File fields
    private String fileName;

    @Lob // Use @Lob to store large binary data like file content
    private byte[] fileContent;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
