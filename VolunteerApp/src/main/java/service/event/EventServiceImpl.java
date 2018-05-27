package service.event;

import dto.EventDto;
import model.Category;
import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EventRepository;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public boolean create(EventDto eventDto) {

        Category category = null;
        String requestedCategory = eventDto.getCategory().toString();

        switch (requestedCategory) {
            case "ADMINISTRATIVE":
                category = Category.ADMINISTRATIVE;
                break;
            case "CHARITY":
                category = Category.CHARITY;
                break;
            case "TEACHING":
                category = Category.TEACHING;
                break;
        }

        Event event = new Event(eventDto.getName(), eventDto.getDate(), eventDto.getLocation(), eventDto.getOrganizer(), eventDto.getNoOfVolunteers(), category);

        eventRepository.save(event);

        return eventRepository.findById(event.getId()).isPresent();
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public boolean updateEvent(Long id) {

        Event event = eventRepository.findById(id).get();

        int availableSeats = event.getNoOfVolunteers();

        if (availableSeats == 0) {
            return false;
        }

        event.setNoOfVolunteers(availableSeats - 1);

        return true;
    }
}
