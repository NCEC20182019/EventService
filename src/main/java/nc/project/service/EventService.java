package nc.project.service;

import nc.project.model.Event;
import nc.project.model.FilterParams;
import nc.project.model.Location;
import nc.project.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EventService {
    Event getById(int eventId);
    List<Event> getAll();
    //сорь за костыль с source_uri, но я хз куда он делся. То ли @Column(name = "source_uri") не работает
    //то ли я зафейлился где-то

    // я починил
    Event createEvent(Event newEvent, Location location);
    Event updateEvent(int eventId,Event updatedEvent, Location location);
    void deleteEvent(int eventId);

    List<Type> getAllTypes();

    Set<Event> filter(FilterParams params);

    ArrayList<Event> getBatchData();
    ArrayList<Event> getEventsByTitleAndSourceUrl(String title, String sourceUri);
}
