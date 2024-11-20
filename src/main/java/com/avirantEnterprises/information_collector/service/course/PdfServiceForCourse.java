package com.avirantEnterprises.information_collector.service.course;

import com.avirantEnterprises.information_collector.model.course.UserForCourse;

//package com.avirantEnterprises.information_collector.service.personal;
//
//import com.avirantEnterprises.information_collector.model.personal.User;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//@Service
//public class PdfService {
//
//    @Autowired
//    private RegistrationService registrationService;
//
//    public byte[] generateUserProfilePdf(Long userId) throws IOException, DocumentException {
//        User user = registrationService.getUserById(userId);
//
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, outputStream);
//        document.open();
//
//        document.add(new Paragraph("User Profile"));
//        document.add(new Paragraph("\n"));
//
//        PdfPTable table = new PdfPTable(2);
//        table.setWidthPercentage(100);
//        table.setSpacingBefore(10f);
//        table.setSpacingAfter(10f);
//
//        table.addCell("Name");
//        table.addCell(user.getName());
//
//        table.addCell("Date of Birth");
//        table.addCell(user.getDob().toString());
//
//        table.addCell("Contact Info");
//        table.addCell(user.getContact());
//
//        document.add(table);
//
//        if (user.getProfilePicPath() != null) {
//            Image image = Image.getInstance(Files.readAllBytes(Paths.get(user.getProfilePicPath())));
//            image.setAlignment(Element.ALIGN_CENTER);
//            image.scaleToFit(100, 400); // Set the size of the image
//            document.add(image);
//        }
//
//        document.close();
//
//        return outputStream.toByteArray();
//    }
//}


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfServiceForCourse {

    @Autowired
    private RegistrationServiceForCourse registrationService;

    private final Path rootLocation = Paths.get("upload-dir");

    public byte[] generateUserProfilePdf(Long userId) throws IOException, DocumentException {
        UserForCourse userForCourse = registrationService.getUserById(userId);

        if (userForCourse == null) {
            throw new RuntimeException("User not found");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("User Profile"));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        table.addCell("Name");
        table.addCell(userForCourse.getName());

        table.addCell("Date of Birth");
        table.addCell(userForCourse.getDob().toString());

        table.addCell("Contact Info");
        table.addCell(userForCourse.getContact());

        table.addCell("Email");
        table.addCell(userForCourse.getEmail());
        table.addCell("USN");
        table.addCell(userForCourse.getUsn());

        document.add(table);

        if (userForCourse.getProfilePicPath() != null) {
            String sanitizedFilePath = rootLocation.resolve(Paths.get(userForCourse.getProfilePicPath()))
                    .normalize().toAbsolutePath().toString();
            Image image = Image.getInstance(Files.readAllBytes(Paths.get(sanitizedFilePath)));
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleToFit(100, 400); // Set the size of the image
            document.add(image);
        }

        document.close();

        return outputStream.toByteArray();
    }
}

