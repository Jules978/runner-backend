package demo.run.run;
import demo.run.run.Run;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RunRepository extends CrudRepository<Run, Long> {
}
