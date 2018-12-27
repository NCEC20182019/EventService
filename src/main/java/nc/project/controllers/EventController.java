package nc.project.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nc.project.models.Event;
import nc.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/event", description = "An API that allows get, create/update and delete events", produces = "application/json")
public class EventController {

  private final EventService eventService;

  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @ApiOperation(value = "get all events", response = Event.class, responseContainer = "List")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Events Details Retrieved", response = Event.class),
    @ApiResponse(code = 500, message = "Internal Server Error"),
    @ApiResponse(code = 404, message = "Events not found")
  })
  @GetMapping(value = "/")
  public List<Event> getAll() {
    return eventService.getAll();
  }

  @GetMapping(value = "/{eventId:\\d+}")
  public Event getEventById(@PathVariable int eventId) {
    return eventService.getById(eventId);
  }

  @PostMapping(value = "/")
  public String createEvent() {
    eventService.createEvent();
    return "";
  }

  @PutMapping(value = "/{eventId:\\d+}")
  public String updateEvent(@PathVariable int eventId) {
    return "";
  }

  @DeleteMapping(value = "/{eventId:\\d+}")
  public String deleteEvent(@PathVariable int eventId) {
    return "";
  }


  // не запускается из-за составного ключа скорее все
}
