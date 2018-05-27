package controller;

import dto.EventDto;
import dto.VolunteerDto;
import model.Event;
import model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import service.event.EventService;
import service.volunteer.VolunteerService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/volunteer")
public class VolunteerController {

    private VolunteerService volunteerService;
    private EventService eventService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService, EventService eventService) {
        this.volunteerService = volunteerService;
        this.eventService = eventService;
    }

    @GetMapping()
    public String getCreate(@ModelAttribute VolunteerDto volunteerDto,  Model model, Principal principal) {

        Volunteer volunteer = volunteerService.findByUsername(principal.getName());

        model.addAttribute("welcomeMessage", "Welcome, " + volunteer.getName() + "!");

        volunteerDto.setName(volunteer.getName());
        volunteerDto.setPhoneNumber(volunteer.getPhoneNumber());
        volunteerDto.setCategory(volunteer.getCategory());
        volunteerDto.setUsername(volunteer.getUsername());
        volunteerDto.setPassword(volunteer.getPassword());
        volunteerDto.setId(volunteer.getId());

        model.addAttribute("volunteerDto", volunteerDto);
        model.addAttribute("eventDto", new EventDto());

        return "volunteer";
    }

    @PostMapping(params = "update")
    public String updateVolunteer(@ModelAttribute @Valid VolunteerDto volunteerDto, BindingResult bindingResult, Model model, Principal principal) {

        model.addAttribute("welcomeMessage", "Welcome, " + volunteerService.findByUsername(principal.getName()).getName() + "!");

        if (bindingResult.hasErrors()) {
            return "volunteer";
        }

        volunteerService.update(volunteerDto);
        model.addAttribute("message", "\t \t You have successfully updated your information!");

        return "volunteer";
    }

    @PostMapping(params = "show")
    public String showEvents(Model model,@ModelAttribute VolunteerDto volunteerDto, @ModelAttribute @Valid EventDto eventDto, BindingResult bindingResult, Principal principal) {

        model.addAttribute("welcomeMessage", "Welcome, " + volunteerService.findByUsername(principal.getName()).getName() + "!");

        if (bindingResult.hasErrors()) {
            System.out.println(getErrors(bindingResult));

        }

        List<Event> list = eventService.findAll();

        model.addAttribute("events", list);

        return "volunteer";
    }

    @PostMapping(params = "apply")
    public String applyEvent(@RequestParam("id") Long id,
                             @ModelAttribute VolunteerDto volunteerDto,
                             @ModelAttribute EventDto eventDto, Model model, Principal principal) {

        model.addAttribute("welcomeMessage", "Welcome, " + volunteerService.findByUsername(principal.getName()).getName() + "!");

        if (eventService.updateEvent(id)) {
            model.addAttribute("message", "BRAVO TU");
        } else {
            model.addAttribute("message", "YOU SUCK");
        }

        return "volunteer";
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
