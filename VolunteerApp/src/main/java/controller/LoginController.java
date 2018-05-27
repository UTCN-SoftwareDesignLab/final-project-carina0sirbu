package controller;

import dto.VolunteerDto;
import model.Admin;
import model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.volunteer.VolunteerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private VolunteerService volunteerService;

    @Autowired
    public LoginController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping()
    public String getCreate(Model model) {

        model.addAttribute("volunteerDto", new VolunteerDto());
        return "login";
    }

    @PostMapping(params="login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Volunteer volunteer,
                        BindingResult result) throws ServletException {
        try {
            RequestCache requestCache = new HttpSessionRequestCache();
            request.login(volunteer.getUsername(), volunteer.getPassword());
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRedirectUrl();
            } else {
                return "redirect:/";
            }
        } catch (ServletException authenticationFailed) {
            result.rejectValue(null, "authentication.failed");
            return "login";
        }
    }

//    @PostMapping(params = "adminPage")
//    public String goToBooks(@ModelAttribute Admin admin) {
//
//        return "redirect:/admin";
//    }
}
