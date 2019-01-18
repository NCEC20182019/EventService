package nc.project.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nc.project.models.Event;
import nc.project.models.Location;
import nc.project.services.EventService;
import nc.project.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "event", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/event", description = "An API that allows get, create/update and delete events", produces = "application/json")
public class EventController {

  private final EventService eventService;
  private final LocationService locationService;

  @Autowired
  public EventController(EventService eventService, LocationService locationService) {
    this.eventService = eventService;
    this.locationService = locationService;

  }

  @ApiOperation(value = "get all events", response = Event.class, responseContainer = "List")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Events Details Retrieved", response = Event.class),
    @ApiResponse(code = 500, message = "Internal Server Error"),
    @ApiResponse(code = 404, message = "Events not found")
  })
  @GetMapping(value = "/")
  public List<Event> getAll() {
    List<Event> list = eventService.getAll();
    return list;
  }

  @GetMapping(value = "/{eventId:\\d+}")
  public Event getEventById(@PathVariable int eventId) {
    return eventService.getById(eventId);
  }

  @PostMapping(value = "/create")
  public Event createEvent(@RequestBody String title, @RequestBody String description, @RequestBody Date date_start,
                            @RequestBody Date date_end, @RequestBody String source_uri, @RequestBody String name_location,
                            @RequestBody String url_to_location) {

    Location location = new Location(name_location,url_to_location);

    return eventService.createEvent(title, description, date_start,
            date_end, source_uri, location);
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
