package nc.project.repository;

import nc.project.model.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface LocationRepository extends CrudRepository<Location,Integer> {
  Location findById(int location_id);
  @Override
  List<Location> findAll();
}
