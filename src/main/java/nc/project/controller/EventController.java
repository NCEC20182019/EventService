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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "event", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Api(value = "/event", description = "An API that allows get, create/update and delete events", produces = "application/json")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;
    private final ModelMapper modelMapper = new ModelMapper();

    private static Logger logger = LoggerFactory.getLogger(EventController.class);

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
        logger.debug("Вход в getAll()");


        List<EventGetDTO> response = new ArrayList<>();
        eventService.getAll().forEach(allEventsList->response.add(modelMapper.map(allEventsList,EventGetDTO.class)));

        logger.debug("Возвращается {} размером {}",response.getClass().getTypeName(), response.size());
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
        logger.debug("Вход в getEventById()");
        logger.debug("Входной параметр eventId {}",eventId);

        EventGetDTO result = modelMapper.map(eventService.getById(eventId),EventGetDTO.class);

        logger.debug("Возвращается объект {}",result);

        return result;
    }

    @ApiOperation(value = "create new event")
    @PostMapping(value = "/create")
    public void createEvent(@RequestBody EventCreateDTO newEvent) {
        logger.debug("Вход в createEvent()");
        logger.debug("Входной параметр newEvent {}",newEvent);
        //modelMapper.addMappings(skipFieldsMap);

        Location location = new Location(newEvent.getName_location());
        Event ev = eventService.createEvent(modelMapper.map(newEvent,Event.class),location);

        logger.debug("Создан event {}", ev);
    }

    @ApiOperation(value = "allows update event")
    @PutMapping(value = "/update/{eventId:\\d+}")
    public void updateEvent(@PathVariable int eventId,@RequestBody EventCreateDTO updatedEvent) {
        logger.debug("Вход в updateEvent()");
        logger.debug("Входные параметры eventId {}, updatedEvent {}",eventId, updatedEvent);

        Location location = new Location(updatedEvent.getName_location());

        logger.debug("Обновленный event {}",eventService.updateEvent(eventId, modelMapper.map(updatedEvent,Event.class),location));
    }

    @ApiOperation(value = "allows delete event")
    @DeleteMapping(value = "/delete/{eventId:\\d+}")
    public void deleteEvent(@PathVariable int eventId) {
        logger.debug("Вход в deleteEvent()");
        logger.debug("Входной пареаметр eventId {}", eventId);

        eventService.deleteEvent(eventId);

        logger.debug("Выход из deleteEvent()");
    }
}
