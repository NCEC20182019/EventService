package nc.project.controller;

import nc.project.model.Event;
import nc.project.model.EventUpdate;
import nc.project.model.InfoForUpdates;
import nc.project.model.dto.EventUpdateCreateDTO;
import nc.project.model.dto.EventUpdateGetDTO;
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
@RequestMapping(value = "updates", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UpdatesController {

    private final static int SIZE_OF_BATCH = 1;

    private final EventUpdateService eventUpdateService;
    private final ModelMapper modelMapper = new ModelMapper();
    private EventUpdate eventUpdate;
    private final EventService eventService;
    private static Logger logger = LoggerFactory.getLogger(UpdatesController.class);

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
        ArrayList<EventUpdate> updatesFromDb = eventUpdateService.getByEventAndTwitterUrl(newUpdate.getUrl_to_tweet(), event);
        if(updatesFromDb.size() == 0){
        eventUpdate = eventUpdateService.createEventUpdate(modelMapper.map(newUpdate, EventUpdate.class), event);
        logger.debug("Создан update {}" + eventUpdate);
        } else {
            for (EventUpdate eu : updatesFromDb){
                eventUpdateService.updateEventUpdate(eu.getId(), modelMapper.map(newUpdate, EventUpdate.class),event);
                logger.debug("update c id " + eu.getId()  + "был обновлен");
            }
        }

    }


    /*
    select events.*
    from events LEFT JOIN event_updates
        ON events.event_id = event_updates.event_id
    WHERE (event_updates.last_update_date < date_trunc('day',current_timestamp) OR event_updates.last_update_date IS NULL)
          AND
          (events.last_updating_date IS NULL OR events.last_updating_date < date_trunc('day',current_timestamp))
    ;
     */
    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    public ArrayList<InfoForUpdates> getBatch(){


        ArrayList<Event> batchData = eventService.getBatchData();
        ArrayList<InfoForUpdates> eventsForUpdate = new ArrayList<>();



        for(int i = 0; i < SIZE_OF_BATCH; i++){
            batchData.get(i).setLastUpdatingDate(new Date());
            eventService.updateEvent(batchData.get(i).getId(), batchData.get(i), batchData.get(i).getLocation());
            eventsForUpdate.add(new InfoForUpdates(batchData.get(i).getId(),batchData.get(i).getTitle(),batchData.get(i).getDateStart(),batchData.get(i).getDateEnd(),batchData.get(i).getTypeOfEvent()));
        }

        if(eventsForUpdate.size() > 0){
            logger.debug("Batch создан " + eventsForUpdate.get(0));
        }
        return eventsForUpdate;
    }


    @RequestMapping(value = "/by_event", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<EventUpdate> getUpdatesByEventId(@RequestParam(value = "id",required = true) int event_id){
        return eventUpdateService.getByEvent(eventService.getById(event_id));
    }


}
