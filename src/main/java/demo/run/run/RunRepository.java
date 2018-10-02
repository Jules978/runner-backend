package demo.run.run;
import demo.run.run.Run;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface RunRepository extends CrudRepository<Run, Long> {
    @Query("SELECT r FROM Run r WHERE r.overdue = 'no'")
    List<Run> findAllOverdueRuns();

    @Query("SELECT r FROM Run r  WHERE r.training.id = :id")
    List<Run> FindRunsByTraining(@Param("id") long id);
}
