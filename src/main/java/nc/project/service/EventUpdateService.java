package nc.project.service;

import nc.project.model.Event;
import nc.project.model.EventUpdate;

import java.util.List;

public interface EventUpdateService {
    EventUpdate getById(int eventUpdateId);
    List<EventUpdate> getAll();
    EventUpdate createEventUpdate(EventUpdate newUpdate, Event event);
    EventUpdate updateEventUpdate(int eventUpdateId, EventUpdate updatedEventUpdate, Event event);
    void deleteEventUpdate(int eventUpdateId);
}
