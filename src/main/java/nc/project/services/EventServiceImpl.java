package nc.project.services;

import nc.project.models.Event;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{

    @Override
    public Event getEvent(int eventId) {
        return new Event("Test_event");
    }
}
