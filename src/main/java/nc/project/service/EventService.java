package nc.project.service;

import nc.project.model.Event;
import nc.project.model.Location;

import java.util.List;

public interface EventService {
    Event getById(int eventId);
    List<Event> getAll();
    Event createEvent(Event newEvent, Location location);
    Event updateEvent(int eventId,Event updatedEvent, Location location);
    void deleteEvent(int eventId);
}
