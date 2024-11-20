package com.avirantEnterprises.information_collector.service.course;

import com.avirantEnterprises.information_collector.model.course.UserForCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.avirantEnterprises.information_collector.repository.course.UserRepositoryForCourse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceForCourse {

    @Autowired
    private UserRepositoryForCourse userRepositoryForCourse;

    private final Path rootLocation = Paths.get("upload-dir");

    public void registerUser(String name, LocalDate dob, String contact,String email,String usn, MultipartFile profilePic) {
        UserForCourse userForCourse = new UserForCourse();
        userForCourse.setName(name);
        userForCourse.setDob(dob);
        userForCourse.setContact(contact);
        userForCourse.setEmail(email);
        userForCourse.setUsn(usn);
        String profilePicPath = saveFile(profilePic);
        userForCourse.setProfilePicPath(profilePicPath);
        userRepositoryForCourse.save(userForCourse);
    }

    public void updateUserProfile(Long id, String name, LocalDate dob, String contact,String email,String usn, MultipartFile profilePic) {
        Optional<UserForCourse> optionalUser = userRepositoryForCourse.findById(id);
        if (optionalUser.isPresent()) {
            UserForCourse userForCourse = optionalUser.get();
            userForCourse.setName(name);
            userForCourse.setDob(dob);
            userForCourse.setContact(contact);
            userForCourse.setEmail(email);
            userForCourse.setUsn(usn);
            if (!profilePic.isEmpty()) {
                String profilePicPath = saveFile(profilePic);
                userForCourse.setProfilePicPath(profilePicPath);
            }
            userRepositoryForCourse.save(userForCourse);
        }
    }

    public UserForCourse getUserById(Long id) {
        return userRepositoryForCourse.findById(id).orElse(null);
    }

    public List<UserForCourse> getAllUsers() {
        return userRepositoryForCourse.findAll();
    }

    public void deleteUserById(Long id) {
        userRepositoryForCourse.deleteById(id);
    }

//    private String saveFile(MultipartFile file) {
//        try {
//            Files.createDirectories(rootLocation);
//            Path destinationFile = rootLocation.resolve(Paths.get(file.getOriginalFilename()))
//                    .normalize().toAbsolutePath();
//            file.transferTo(destinationFile);
//            return file.getOriginalFilename();  // Store only the file name
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to store file.", e);
//        }
//    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }

    private String saveFile(MultipartFile file) {
        try {
            Files.createDirectories(rootLocation);
            String sanitizedFileName = sanitizeFileName(file.getOriginalFilename());
            Path destinationFile = rootLocation.resolve(Paths.get(sanitizedFileName))
                    .normalize().toAbsolutePath();
            file.transferTo(destinationFile);
            return sanitizedFileName;  // Store only the sanitized file name
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }




}
