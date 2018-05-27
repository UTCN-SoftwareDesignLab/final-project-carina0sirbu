package controller;

import dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.event.EventService;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
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

//        List<Book> bookList = StreamSupport.stream(bookRepository.findAll().spliterator(),false)
//                .filter(b->b.getQuantity()==0)
//                .collect(Collectors.toCollection(ArrayList::new));

        return "/event";
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
