package controller;

import dto.AdminDto;
import dto.EventDto;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.admin.AdminService;

import javax.servlet.ServletException;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public String getCreate(Model model) {

        model.addAttribute("adminDto", new AdminDto());
        return "admin";
    }

    @PostMapping(params="login")
    public String login(@ModelAttribute @Valid AdminDto adminDto, BindingResult bindingResult, @ModelAttribute EventDto eventDto) {

        if (bindingResult.hasErrors()) {
            return "admin";
        }

        System.out.println(adminDto.getUsername());

        Admin admin = adminService.findByUsername(adminDto.getUsername());

        System.out.println(admin);

        if (admin.getRole().toString().equals("ADMIN")) {
            return "/event";
        }

        return "/admin";
    }
}
