package controller;

import dto.VolunteerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.volunteer.VolunteerService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private VolunteerService volunteerService;

    @Autowired
    public RegisterController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping()
    public String getCreate(@ModelAttribute VolunteerDto volunteerDto, Model model) {

        model.addAttribute("volunteerDto", new VolunteerDto());
        return "register";
    }

    @PostMapping(params = "save")
    public String save(@ModelAttribute @Valid VolunteerDto volunteerDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(getErrors(bindingResult));
            return "register";
        }

//        List<String> allTypes = new ArrayList<>();
//        allTypes.add("ADMINISTRATION");
//        allTypes.add("CHARITY");
//        allTypes.add("TEACHING");
//
//        model.addAttribute("allTypes", allTypes);

        volunteerService.create(volunteerDto);

        model.addAttribute("message", "You have successfully registered! ");

        return "register";
    }

    private String getErrors(BindingResult bindingResult) {
        String message = "";
        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError e : errors) {
            message += "ERROR: " + e.getDefaultMessage();
        }
        return message;
    }
}
