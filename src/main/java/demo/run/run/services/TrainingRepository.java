package demo.run.run.services;

import demo.run.run.entities.Training;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long> {
}
