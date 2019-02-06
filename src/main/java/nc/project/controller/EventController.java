package nc.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nc.project.model.Event;
import nc.project.model.Location;
import nc.project.model.dto.EventCreateDTO;
import nc.project.model.dto.EventGetDTO;
import nc.project.service.EventService;
import nc.project.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "event", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/event", description = "An API that allows get, create/update and delete events", produces = "application/json")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }
    /*private PropertyMap<Event, EventCreateDTO> skipFieldsMap = new PropertyMap<Event, EventCreateDTO>() {
        protected void configure() {
            skip().getName_location();
        }
    };*/

    @ApiOperation(value = "get all events", response = EventGetDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Events Details Retrieved", response = EventGetDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Events not found")
    })
    @GetMapping(value = "/")
    public List<EventGetDTO> getAll() {
        List<EventGetDTO> response = new ArrayList<>();
        eventService.getAll().forEach(allEventsList->response.add(modelMapper.map(allEventsList,EventGetDTO.class)));
        return response;
    }

    @ApiOperation(value = "get event by id", response = EventGetDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event Details Retrieved", response = EventGetDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    @GetMapping(value = "/{eventId:\\d+}")
    public EventGetDTO getEventById(@PathVariable int eventId) {
        return modelMapper.map(eventService.getById(eventId),EventGetDTO.class);
    }

    @ApiOperation(value = "create new event")
    @PostMapping(value = "/create")
    public void createEvent(@RequestBody EventCreateDTO newEvent) {

        //modelMapper.addMappings(skipFieldsMap);

        Location location = new Location(newEvent.getName_location());

        eventService.createEvent(modelMapper.map(newEvent,Event.class),location);
    }

    @PutMapping(value = "/{eventId:\\d+}")
    public void updateEvent(@PathVariable int eventId,@RequestBody EventCreateDTO updatedEvent) {
        Location location = new Location(updatedEvent.getName_location());

        eventService.updateEvent(eventId, modelMapper.map(updatedEvent,Event.class),location);
    }

    @DeleteMapping(value = "/{eventId:\\d+}")
    public void deleteEvent(@PathVariable int eventId) {
        eventService.deleteEvent(eventId);
    }
}
