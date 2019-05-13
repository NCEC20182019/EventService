package nc.project.repository;

import nc.project.model.Event;
import nc.project.model.EventUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UpdatesRepository extends CrudRepository<EventUpdate, Integer> {
    EventUpdate findById (int update_id);
    ArrayList<EventUpdate> findByEvent(Event event_id);
}
