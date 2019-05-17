package nc.project.service;

import nc.project.model.Event;
import nc.project.model.Location;
import nc.project.model.SortingAndFilteringParams;
import nc.project.model.Type;
import nc.project.model.dto.EventGetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Event getById(int eventId);
    List<Event> getAll();
    Event createEvent(Event newEvent, Location location);
    Event updateEvent(int eventId,Event updatedEvent, Location location);
    void deleteEvent(int eventId);

    List<Type> getAllTypes();

    List<EventGetDTO> sortAndFilter(SortingAndFilteringParams params);
}
