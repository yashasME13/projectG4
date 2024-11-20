package com.avirantEnterprises.information_collector.controller.vin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // General dashboard where users can choose between Admin or Student
    @GetMapping("/dashboard")
    public String generalDashboard() {
        return "general-dashboard"; // View that contains buttons for Admin and Student
    }

    // Redirect to Admin Dashboard
    @GetMapping("/dashboard/admin")
    public String adminRedirect() {
        return "redirect:/admin/dashboard";
    }

    // Redirect to Student Dashboard
    @GetMapping("/dashboard/student")
    public String studentRedirect() {
        return "redirect:/student/dashboard";
    }
}
