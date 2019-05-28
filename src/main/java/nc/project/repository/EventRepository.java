package nc.project.repository;

import nc.project.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
  Event findById(int event_id);

    List<Event> findAllByTypeIn(List<String> types);

  List<Event> findAllByDateStartGreaterThanAndDateEndLessThan(Date start, Date end);

  @Query(value = "select s.* from events s " +
          "join locations l on " +
          "l.location_id = s.location_id " +
          "where (point(:lat,:lng) <@ circle(point(l.latitude,l.longitude),:radius))", nativeQuery = true)
  List<Event> findAllByAreaFilter(@Param("lat") double lat,
                                  @Param("lng") double lng,
                                  @Param("radius") double radius);
  @Query(value = "select events.*\n" +
          "from events LEFT JOIN event_updates\n" +
          "    ON events.event_id = event_updates.event_id\n" +
          "WHERE (event_updates.last_update_date < date_trunc('day',current_timestamp) OR event_updates.last_update_date IS NULL)\n" +
          "      AND\n" +
          "      (events.last_updating_date IS NULL OR events.last_updating_date < date_trunc('day',current_timestamp))", nativeQuery = true)
  ArrayList<Event> getDataForBatch();

    ArrayList<Event> findEventsByTitleAndSourceUri(String title, String sourceUri);
}
