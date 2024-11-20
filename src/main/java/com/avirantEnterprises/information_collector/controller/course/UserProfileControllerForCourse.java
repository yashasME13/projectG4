package com.avirantEnterprises.information_collector.controller.course;

import com.avirantEnterprises.information_collector.model.course.UserForCourse;
import com.avirantEnterprises.information_collector.service.course.RegistrationServiceForCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileControllerForCourse {

    @Autowired
    private RegistrationServiceForCourse registrationServiceForCourse;

    @GetMapping("/profileForCourse/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        UserForCourse userForCourse = registrationServiceForCourse.getUserById(id);
        System.out.println("Profile Picture Path: " + userForCourse.getProfilePicPath());
        model.addAttribute("userForCourse", userForCourse);
        return "course/profile_view_course"; // View template for displaying the user profile
    }
}
