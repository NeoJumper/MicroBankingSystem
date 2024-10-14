package com.kcc.banking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("test", "test");
        return "index";
    }

    @GetMapping("/page/error/error-404")
    public String error404(Model model) {
        return "error-404";
    }

    @GetMapping("/page/error/error-403")
    public String error403(Model model) {
        return "error-403";
    }

    @GetMapping("/admin/employee-management")
    public String employeeManagement(Model model) {

        return "employee-management";
    }

}
