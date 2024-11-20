package com.avirantEnterprises.information_collector.controller.course;

import com.avirantEnterprises.information_collector.service.course.RegistrationServiceForCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileDeleteControllerForCourse {

    @Autowired
    private RegistrationServiceForCourse registrationServiceForCourse;

    @GetMapping("/profileForCourse/delete/{id}")
    public String deleteUserProfile(@PathVariable Long id) {
        registrationServiceForCourse.deleteUserById(id);
        return "redirect:course/profile_list_course"; // Redirect to a list of profiles or another appropriate page
    }
}
