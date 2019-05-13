package nc.project.controller;

import nc.project.model.Event;
import nc.project.model.EventUpdate;
import nc.project.model.InfoForUpdates;
import nc.project.model.dto.EventUpdateCreateDTO;
import nc.project.model.dto.EventUpdateGetDTO;
import nc.project.repository.EventRepository;
import nc.project.service.EventService;
import nc.project.service.EventUpdateService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UpdatesController {

    private final EventUpdateService eventUpdateService;
    private final ModelMapper modelMapper = new ModelMapper();
    private EventUpdate eventUpdate;
    private final EventService eventService;
    private static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public UpdatesController(EventUpdateService eventUpdateService, EventService eventService) {
        this.eventUpdateService = eventUpdateService;
        this.eventService = eventService;
        eventUpdate = null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<EventUpdateGetDTO> getAllUpdates(){
        List<EventUpdateGetDTO> response = new ArrayList<>();
        eventUpdateService.getAll().forEach(new Consumer<EventUpdate>() {
            @Override
            public void accept(EventUpdate allUpdatesList) {
                response.add(modelMapper.map(allUpdatesList, EventUpdateGetDTO.class));
            }
        });
        return response;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createUpdate(@RequestBody EventUpdateCreateDTO newUpdate){
        Event event = eventService.getById(newUpdate.getEvent_id());
        ArrayList<EventUpdate> updatesFromDb = eventUpdateService.getByEvent(event);
        if (updatesFromDb.size() == 0){
        eventUpdate = eventUpdateService.createEventUpdate(modelMapper.map(newUpdate, EventUpdate.class), event);
        logger.debug("Создан update {}", eventUpdate);
        } else {
            for (EventUpdate eu : updatesFromDb)
                eventUpdateService.updateEventUpdate(eu.getId(), modelMapper.map(newUpdate, EventUpdate.class));
            logger.debug("update c id " + eventUpdate.getId()  + "был обновлен");
        }

    }



    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    public ArrayList<InfoForUpdates> getBatch(){
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        ArrayList<InfoForUpdates> eventsForUpdate = new ArrayList<>();

        List<Event> events = eventService.getAll();
        ArrayList<Event> tmp = new ArrayList<>();

        List<EventUpdate> updates = eventUpdateService.getAll();
        Boolean hasUpdates;
/*
Вместо
select events.*
    from events LEFT JOIN event_updates
        ON events.event_id = event_updates.event_id
      WHERE event_updates.last_update_date < date_trunc('day',current_timestamp);
 */
        for(Event e : events){
            hasUpdates = false;
            for (EventUpdate eu : updates) {
                System.out.println(eu.getLast_update_date());
                System.out.println(date);
                if (eu.getLast_update_date() != null) System.out.println(eu.getLast_update_date().before(date));
                if (e.equals(eu.getEvent()) && !tmp.contains(e) && eu.getLast_update_date() != null && eu.getLast_update_date().before(date)) {hasUpdates = true; tmp.add(e);}
                if (e.equals(eu.getEvent())) hasUpdates = true;
            }
            if (!hasUpdates) tmp.add(e);
            if(tmp.size() > 5) break;//размер батча 5
        }
        for (Event e : tmp)
            eventsForUpdate.add(new InfoForUpdates(e.getId(),e.getTitle(),e.getDate_start(),e.getDate_end(),e.getType()));
        return eventsForUpdate;
    }

    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println("d1=" + d1);
        Date d = new Date();
        d.setHours(0);
        System.out.println("d=" + d);
        System.out.println(d1.before(d));

    }

    @RequestMapping(value = "/by_event", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<EventUpdate> getUpdatesByEventId(@RequestParam(value = "id",required = true) int event_id){
        return eventUpdateService.getByEvent(eventService.getById(event_id));
    }


}
