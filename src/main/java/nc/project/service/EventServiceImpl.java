package nc.project.service;

import nc.project.model.Event;
import nc.project.model.FilterParams;
import nc.project.model.Location;
import nc.project.model.Type;
import nc.project.repository.EventRepository;
import nc.project.repository.TypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

  private EventRepository eventRepo;
  private LocationService locService;
  private TypeRepository typeRepo;

  @Autowired
  public EventServiceImpl(EventRepository eventRepo, LocationService locService, TypeRepository typeRepo) {
    this.eventRepo = eventRepo;
    this.locService = locService;
    this.typeRepo = typeRepo;
  }

  public Event getById(int eventId) {
    return eventRepo.findById(eventId);
  }


  public List<Event> getAll() {
    List<Event> list = new ArrayList<>();
    eventRepo.findAll().forEach(list::add);
    return list;
  }

  @Override
  public Event createEvent(Event newEvent, Location location) {
    //Event newEvent = new Event(title,description,date_start,date_end,source_uri);

    newEvent.setLocation(locService.checkLocation(location));

    // logger.debug(ex.getMessage());
    return eventRepo.save(newEvent);
  }

  @Override
  public Event updateEvent(int eventId, Event updatedEvent, Location location) {
      Event eventFromDb = eventRepo.findById(eventId);

      BeanUtils.copyProperties(updatedEvent, eventFromDb, "id", "localizations", "location");

      if (!eventFromDb.getLocation().equals(location))
          eventFromDb.setLocation(locService.checkLocation(location));

    if (updatedEvent.getLocalizations() != null)
        eventFromDb.setLocalizations(updatedEvent.getLocalizations());

      return eventRepo.save(eventFromDb);
  }

  public void deleteEvent(int eventId) {
    eventRepo.deleteById(eventId);
  }

  @Override
  public List<Type> getAllTypes() {
    return typeRepo.findAllByOrderById();
  }

  @Override
  public Set<Event> filter(FilterParams params) {
      Set<Event> eventSet = new HashSet<>();
    if (params.isTypeFilter()) {
        eventSet.addAll(eventRepo.findAllByTypeIn(params.getTypes()));
    }
    if (params.isDateFilter()) {
        eventSet.addAll(eventRepo.findAllByDateStartGreaterThanAndDateEndLessThan(
                params.getDateFrom(), params.getDateTo()));
    } else if (params.isDateFromFilter()) {
        eventSet.addAll(eventRepo.findAllByDateStartGreaterThanEqual(params.getDateFrom()));
    } else if (params.isDateToFilter()) {
        eventSet.addAll(eventRepo.findAllByDateEndLessThanEqual(params.getDateTo()));
    }
    if (params.isAreaFilter()) {
        eventSet.addAll(eventRepo.findAllByAreaFilter(
                params.getArea().getLatitude(),
                params.getArea().getLongitude(),
                params.getArea().getRadius()));
    }
      return eventSet;
  }
  @Override
  public ArrayList<Event> getBatchData() {
    return eventRepo.getDataForBatch();
  }

  @Override
  public ArrayList<Event> getEventsByTitleAndSourceUrl(String title, String sourceUri) {
    return eventRepo.findEventsByTitleAndSourceUri(title, sourceUri);
  }
}
