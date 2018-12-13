package nc.project.service;

import nc.project.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EventServiceMock implements EventService{

    @Override
    public Event getEvent(int eventId) {
        return new Event(1,"Test_event");
    }
}
