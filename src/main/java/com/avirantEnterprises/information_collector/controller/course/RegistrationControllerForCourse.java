package com.avirantEnterprises.information_collector.controller.course;

import com.avirantEnterprises.information_collector.service.course.RegistrationServiceForCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller("courseRegistrationController")
public class RegistrationControllerForCourse {

    @Autowired
    private RegistrationServiceForCourse registrationServiceForCourse;

    @GetMapping("/courseReg")
    public String showRegistrationForm() {
        return "course/course_registration";
    }

    @PostMapping("/courseregsuccess")
    public String register(@RequestParam("name") String name,
                           @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                           @RequestParam("contact") String contact,
                           @RequestParam("email") String email,
                           @RequestParam("usn") String usn,
                           @RequestParam("profilePic") MultipartFile profilePic) {
        registrationServiceForCourse.registerUser(name, dob, contact,email,usn,profilePic);
        return "course/success";
    }
}
