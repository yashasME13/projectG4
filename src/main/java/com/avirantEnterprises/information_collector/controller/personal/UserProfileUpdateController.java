package com.avirantEnterprises.information_collector.controller.personal;

import com.avirantEnterprises.information_collector.model.personal.User;
import com.avirantEnterprises.information_collector.service.personal.RegistrationService;
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
public class UserProfileUpdateController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/profile/update/{id}")
    public String showUpdateProfileForm(@PathVariable Long id, Model model) {
        User user = registrationService.getUserById(id);
        model.addAttribute("user", user);
        return "personal/profile_update"; // Reference the correct path to your template
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                                @RequestParam("contact") String contact,
                                @RequestParam("knowledge") String knowledge,
                                @RequestParam("clarity") String clarity,
                                @RequestParam("overallrating") String overallrating,
                                @RequestParam("useful") String useful,
                                @RequestParam("profilePic") MultipartFile profilePic) {
        registrationService.updateUserProfile(id, name, dob, contact,knowledge,clarity,overallrating,useful, profilePic);
        return "redirect:/profile/" + id; // Redirect to the updated profile page
    }
}
