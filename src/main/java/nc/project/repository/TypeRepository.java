package nc.project.repository;

import nc.project.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Integer> {
  List<Type> findAllByOrderById();
}
