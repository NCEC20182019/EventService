package nc.project.services;

import nc.project.models.Event;
import nc.project.repository.EventRepository;
import nc.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private LocationRepository locationRepo;

    @Override
    public Event getById(int eventId) {
        return null; //new Event("Test_event");;
    }

    @Override
    public List<Event> getAll() {
        return null; // eventRepo.findAll();
    }

    @Override
    public void createEvent() {

    }
}
