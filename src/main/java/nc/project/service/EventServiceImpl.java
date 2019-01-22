package nc.project.service;

import nc.project.model.Event;
import nc.project.model.Location;
import nc.project.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{

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


        if (!locService.isLocationExist(location))
            locService.createLocation(location);

        newEvent.setLocation(location);

        return eventRepo.save(newEvent);
    }
}
