package nc.project.service;

import nc.project.model.*;
import nc.project.model.dto.EventGetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface EventService {
    Event getById(int eventId);
    List<Event> getAll();
    //сорь за костыль с source_uri, но я хз куда он делся. То ли @Column(name = "source_uri") не работает
    //то ли я зафейлился где-то
    Event createEvent(Event newEvent, Location location, String source_uri);
    Event updateEvent(int eventId,Event updatedEvent, Location location);
    void deleteEvent(int eventId);

    List<Type> getAllTypes();

    List<EventGetDTO> sortAndFilter(SortingAndFilteringParams params);

    ArrayList<Event> getBatchData();
    ArrayList<Event> getEventsByTitleAndSourceUrl(String title, String sourceUri);
}
