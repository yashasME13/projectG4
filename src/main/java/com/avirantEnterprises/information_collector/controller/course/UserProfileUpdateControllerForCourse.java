package com.avirantEnterprises.information_collector.controller.course;

import com.avirantEnterprises.information_collector.model.course.UserForCourse;
import com.avirantEnterprises.information_collector.service.course.RegistrationServiceForCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
public class UserProfileUpdateControllerForCourse {

    @Autowired
    private RegistrationServiceForCourse registrationServiceForCourse;

    @GetMapping("/profileForCourse/update/{id}")
    public String showUpdateProfileForm(@PathVariable Long id, Model model) {
        UserForCourse userForCourse = registrationServiceForCourse.getUserById(id);
        model.addAttribute("userForCourse", userForCourse);
        return "course/profile_update_course"; // Reference the correct path to your template
    }
    @PostMapping("/updateProfileForCourse")
    public String updateProfile(@RequestParam("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                                @RequestParam("contact") String contact,
                                @RequestParam("email") String email,
                                @RequestParam("usn") String usn,
                                @RequestParam("profilePic") MultipartFile profilePic) {
        registrationServiceForCourse.updateUserProfile(id, name, dob, contact,email,usn, profilePic);
        return "redirect:/profileForCourse/" + id; // Redirect to the updated profile page
    }
}
