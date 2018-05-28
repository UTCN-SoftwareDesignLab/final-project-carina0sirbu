package controller;

import constants.Constants;
import dto.EventDto;
import model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import service.email.SendEmail;
import service.event.EventService;
import service.volunteer.VolunteerService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "event")
public class EventController {

    private EventService eventService;
    private VolunteerService volunteerService;
    private SendEmail sendEmail;

    @Autowired
    public EventController(EventService eventService, SendEmail sendEmail, VolunteerService volunteerService) {
        this.eventService = eventService;
        this.sendEmail = sendEmail;
        this.volunteerService = volunteerService;
    }

    @GetMapping()
    public String getPage(@ModelAttribute EventDto eventDto, Model model) {

        model.addAttribute("eventDto", new EventDto());

        return "event";
    }

    @PostMapping(params = "save")
    public String saveEvent(@ModelAttribute @Valid EventDto eventDto, BindingResult bindingResult, Model model) {

        System.out.println(eventDto.toString());

        if (bindingResult.hasErrors()) {
            System.out.println(getErrors(bindingResult));
            return "/event";
        }

        eventService.create(eventDto);

        return "/event";
    }

    @PostMapping(params = "email")
    @Scheduled(fixedDelay = 8000)
    public String sendEmail(@ModelAttribute EventDto eventDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(getErrors(bindingResult));
            return "/event";
        }

        System.out.println(eventDto.getCategory());

        List<Volunteer> list = volunteerService.findAllByCategory(eventDto.getCategory());

        String message = "We have great news! " + eventDto.getOrganizer() + " needs " + eventDto.getNoOfVolunteers() +
                            " people to help them organize a new event, " + eventDto.getName() +
                            ", starting from " + eventDto.getDate() + ", location " + eventDto.getLocation() + ". " +
                            "You are seeing this because you have subscribed to this category of events.";

        for (Volunteer v: list) {
            try {
                sendEmail.send(Constants.EmailInfo.USERNAME, v.getUsername(), Constants.EmailInfo.PASSROWD, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "event";
    }

    private String getErrors(BindingResult bindingResult){
        String message = "";
        List<ObjectError> errors = bindingResult.getAllErrors();
        for( ObjectError e : errors){
            message += "ERROR: " + e.getDefaultMessage();
        }
        return message;
    }
}
