package nc.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nc.project.model.*;
import nc.project.model.dto.EventCreateDTO;
import nc.project.model.dto.EventGetDTO;
import nc.project.service.EventService;
import nc.project.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Api(value = "/events", description = "An API that allows get, create/update and delete events", produces = "application/json")
public class EventController {

    private final EventService eventService;
    private final NotificationService notificationService;
    private final ModelMapper modelMapper = new ModelMapper();
    private Event event;

    private static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public EventController(EventService eventService, NotificationService notificationService) {
        this.eventService = eventService;
        this.notificationService = notificationService;
        event = null;
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
    @GetMapping
    public List<EventGetDTO> getAll() {
        logger.debug("Вход в getAll()");


        List<EventGetDTO> response = new ArrayList<>();
        eventService.getAll().forEach(event -> response.add(modelMapper.map(event, EventGetDTO.class)));

        logger.debug("Возвращается {} размером {}", response.getClass().getTypeName(), response.size());
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
        logger.debug("Входной параметр eventId {}", eventId);

        EventGetDTO result = modelMapper.map(eventService.getById(eventId), EventGetDTO.class);

        logger.debug("Возвращается объект {}", result);

        return result;
    }

    @ApiOperation(value = "get sorted and filtered events", response = EventGetDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Events Details Retrieved", response = EventGetDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Events not found")
    })
    @PostMapping
    public List<EventGetDTO> getSortedAndFiltered(@RequestBody SortingAndFilteringParams params) {
        logger.debug("Вход в SortedAndFiltered()");


        List<EventGetDTO> response = eventService.sortAndFilter(params);

        logger.debug("Возвращается {} размером {}", response.getClass().getTypeName(), response.size());
        return response;
    }

    @ApiOperation(value = "get all event types", response = Type.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event types Retrieved", response = Type.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Types not found")
    })
    @GetMapping(value = "/types")
    public List<Type> getAllTypes() {
        logger.debug("Вход в getAllTypes()");

        List<Type> response = eventService.getAllTypes();

        logger.debug("Возвращается объект {}", response);

        return response;
    }

    @ApiOperation(value = "create new event")
    @PostMapping(value = "/create")
    public void createEvent(@RequestBody EventCreateDTO newEvent) {
        logger.debug("Вход в createEvent()");
        logger.debug("Входной параметр newEvent {}", newEvent);

        Location location = new Location(newEvent.getName_location(), newEvent.getLatitude(), newEvent.getLongitude());
        event = eventService.createEvent(modelMapper.map(newEvent, Event.class), location);

        notificationService.triggerNotificationService(event, TriggerFlags.CREATE);

        logger.debug("Создан event {}", event);
    }

    @ApiOperation(value = "allows update event")
    @PutMapping(value = "/update/{eventId:\\d+}")
    public void updateEvent(@PathVariable int eventId, @RequestBody EventCreateDTO updatedEvent) {
        logger.debug("Вход в updateEvent()");
        logger.debug("Входные параметры eventId {}, updatedEvent {}", eventId, updatedEvent);

        Location location = new Location(updatedEvent.getName_location(), updatedEvent.getLatitude(), updatedEvent.getLongitude());
        event = eventService.updateEvent(eventId, modelMapper.map(updatedEvent, Event.class), location);

        notificationService.triggerNotificationService(event, TriggerFlags.MODIFY);

        logger.debug("Обновленный event {}", event );
    }

    @ApiOperation(value = "allows delete event")
    @DeleteMapping(value = "/delete/{eventId:\\d+}")
    public void deleteEvent(@PathVariable int eventId) {
        logger.debug("Вход в deleteEvent()");
        logger.debug("Входной пареаметр eventId {}", eventId);

        eventService.deleteEvent(eventId);

        notificationService.triggerNotificationService(new Event(eventId), TriggerFlags.DELETE);

        logger.debug("Выход из deleteEvent()");
    }
}
