package nc.project.repository;

import nc.project.models.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Integer> {
  List<Location> findById(int location_id);
}
