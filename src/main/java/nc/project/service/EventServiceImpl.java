package nc.project.service;

import nc.project.model.Event;
import nc.project.model.Location;
import nc.project.model.SortingAndFilteringParams;
import nc.project.model.Type;
import nc.project.model.dto.EventGetDTO;
import nc.project.repository.EventRepository;
import nc.project.repository.TypeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  private final ModelMapper modelMapper;
  private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

  @Autowired
  public EventServiceImpl(EventRepository eventRepo, LocationService locService, TypeRepository typeRepo) {
    this.eventRepo = eventRepo;
    this.locService = locService;
    this.typeRepo = typeRepo;
    modelMapper = new ModelMapper();
  }

  public Event getById(int eventId) {
    return eventRepo.findById(eventId);
  }


  public List<Event> getAll() {
    List<Event> list = new ArrayList<>();
    eventRepo.findAll().forEach(list::add);
    return list;
  }


  public Event createEvent(Event newEvent, Location location) {
    //Event newEvent = new Event(title,description,date_start,date_end,source_uri);

    newEvent.setLocation(locService.checkLocation(location));
    // logger.debug(ex.getMessage());
    return eventRepo.save(newEvent);
  }


  public Event updateEvent(int eventId, Event updatedEvent, Location location) {
    Event eventFromDD = eventRepo.findById(eventId);

    BeanUtils.copyProperties(updatedEvent, eventFromDD, "id", "localizations", "location");

    if (!eventFromDD.getLocation().equals(location))
      eventFromDD.setLocation(locService.checkLocation(location));

    if (updatedEvent.getLocalizations() != null)
      eventFromDD.setLocalizations(updatedEvent.getLocalizations());

    return eventRepo.save(eventFromDD);
  }

  public void deleteEvent(int eventId) {
    eventRepo.deleteById(eventId);
  }

  @Override
  public List<Type> getAllTypes() {
    return typeRepo.findAllByOrderById();
  }

  @Override
  public List<EventGetDTO> sortAndFilter(SortingAndFilteringParams params) {
    Set<EventGetDTO> eventSet = new HashSet<>();
    if (params.getFilter().isTypeFilter()) {
      eventRepo.findAllByTypeIn(params.getFilter().getTypes()).forEach(e -> eventSet.add(modelMapper.map(e, EventGetDTO.class)));
    }
    if (params.getFilter().isDateFilter()) {
      eventRepo.findAllByDateStartGreaterThanAndDateEndLessThan(
              params.getFilter().getDateFrom(), params.getFilter().getDateTo()).forEach(
              e -> eventSet.add(modelMapper.map(e, EventGetDTO.class))
      );
    }
    if (params.getFilter().isAreaFilter()) {
      eventRepo.findAllByAreaSorting(
              params.getFilter().getArea().getCenter().getLatitude(),
              params.getFilter().getArea().getCenter().getLongitude(),
              params.getFilter().getArea().getRadius()).forEach(
              e -> eventSet.add(modelMapper.map(e, EventGetDTO.class))
      );
    }
    return new ArrayList<>(eventSet);
  }
}
