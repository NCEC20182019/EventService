package nc.project.controllers;

import nc.project.models.Event;
import nc.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/{eventId:\\d+}")
    public Event getProfile(@PathVariable int eventId) {
        return eventService.getEvent(eventId);
    }

    // swagger config
    // один endpoint
        // create    put; response 200 OK and id into response-body
        // getById   get
        // delete    delete

    // getAll
}
