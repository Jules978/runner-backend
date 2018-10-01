package demo.run.run;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @PostMapping("/api/training/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postStudent(@RequestBody Training training){
        if(training != null){
            Training result = this.trainingService.save(training);
            return Response.accepted(result.getId()).build();
        }
        System.out.println("Training in POST is null!");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
