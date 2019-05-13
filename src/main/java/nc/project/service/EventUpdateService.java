package nc.project.service;

import nc.project.model.Event;
import nc.project.model.EventUpdate;

import java.util.ArrayList;
import java.util.List;

public interface EventUpdateService {
    EventUpdate getById(int eventUpdateId);
    ArrayList<EventUpdate> getByEvent(Event event);
    List<EventUpdate> getAll();
    EventUpdate createEventUpdate(EventUpdate newUpdate, Event event);
    EventUpdate updateEventUpdate(int eventUpdateId, EventUpdate updatedEventUpdate);
    void deleteEventUpdate(int eventUpdateId);
}
