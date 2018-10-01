package demo.run.run;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class TrainingEndpoint {
    @Autowired
    TrainingService trainingService;

    @GetMapping("/api/training")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Training> showAll() {
        Iterable<Training> trainings = this.trainingService.giveAll();
        return trainings;
    }

    @GetMapping("/api/training/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Training findTrainingById(@PathVariable long id) {
        Training training = this.trainingService.findById(id);
        return training;
    }
}
