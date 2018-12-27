package nc.project.services;

import nc.project.models.Event;

import java.util.List;

public interface EventService {
    Event getById(int eventId);
    List<Event> getAll();
    void createEvent();
}
