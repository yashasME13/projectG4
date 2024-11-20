package com.avirantEnterprises.information_collector.controller.course;

import com.avirantEnterprises.information_collector.model.course.UserForCourse;
import com.avirantEnterprises.information_collector.service.course.RegistrationServiceForCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileListControllerForCourse {

    @Autowired
    private RegistrationServiceForCourse registrationServiceForCourse;

    @GetMapping("/profileListForCourse")
    public String listUserProfiles(Model model) {
        List<UserForCourse> usersForCourse = registrationServiceForCourse.getAllUsers();
        model.addAttribute("users", usersForCourse);
        return "course/profile_list_course";
    }
}
