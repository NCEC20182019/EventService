package nc.project.UnusualStatements;

import nc.project.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
/*
select events.*
    from events LEFT JOIN event_updates
        ON events.event_id = event_updates.event_id
      WHERE event_updates.last_update_date < date_trunc('day',current_timestamp);
 */

/*@Repository
public interface CustomizedEventCrudRepository extends CrudRepository<Event, Integer>, CustomizedEvent<Event> {

    @Query("select e from events e left join event_updates on events.event_id=event_updates.event_id where event_updates.last_update_date < date_trunc('day', current_timestamp)")
    List<Event> findEventsForUpdate();
}*/


