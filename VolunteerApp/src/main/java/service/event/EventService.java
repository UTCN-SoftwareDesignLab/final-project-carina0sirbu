package service.event;

import dto.EventDto;
import model.Event;

import java.util.List;

public interface EventService {

    boolean create(EventDto eventDto);

    List<Event> findAll();

    boolean updateEvent(Long id);

    Event findById(Long id);
}
