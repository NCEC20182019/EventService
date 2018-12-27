package nc.project.repository;

import nc.project.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
  List<Event> findById(int event_id);
}
