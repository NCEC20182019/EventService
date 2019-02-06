package nc.project.service;

import nc.project.model.Event;
import nc.project.model.Location;
import nc.project.repository.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepo;
    @Autowired
    LocationService locService;

    public Event getById(int eventId) {
        return eventRepo.findById(eventId);
    }


    public List<Event> getAll() {
        List<Event> list = new ArrayList<>();
        eventRepo.findAll().forEach(list::add);
        return list;
    }


    public Event createEvent(Event newEvent, Location location) {
        //Event newEvent = new Event(title,description,date_start,date_end,source_uri);

        newEvent.setLocation(locService.checkLocation(location));
        return eventRepo.save(newEvent);
    }


    public Event updateEvent(int eventId, Event updatedEvent, Location location) {
        Event eventFromDD = eventRepo.findById(eventId);

        BeanUtils.copyProperties(updatedEvent, eventFromDD, "id", "localizations","location");

        if(!eventFromDD.getLocation().equals(location))
            eventFromDD.setLocation(locService.checkLocation(location));

        if (updatedEvent.getLocalizations() != null)
            eventFromDD.setLocalizations(updatedEvent.getLocalizations());

        return eventRepo.save(eventFromDD);
    }

    public void deleteEvent(int eventId) {
        eventRepo.deleteById(eventId);
    }
}
