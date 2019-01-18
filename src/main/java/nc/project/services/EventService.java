package nc.project.services;

import nc.project.models.Event;
import nc.project.models.Location;

import java.util.Date;
import java.util.List;

public interface EventService {
    Event getById(int eventId);
    List<Event> getAll();
    Event createEvent(String title, String description, Date date_start,
                     Date date_end, String source_uri, Location location);
}
