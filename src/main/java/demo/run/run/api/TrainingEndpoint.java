package demo.run.run.api;
import demo.run.run.entities.Run;
import demo.run.run.services.RunService;
import demo.run.run.entities.Training;
import demo.run.run.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class TrainingEndpoint {
    @Autowired
    TrainingService trainingService;

    @Autowired
    RunService runService;

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

    @GetMapping("/api/training/runs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Run> findRunsinTraining(@PathVariable long id) {
        List<Run> runs = this.runService.FindByTraining(id);
        return runs;
    }

    @PutMapping("/api/training/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTraining(@PathVariable long id, @RequestBody Training trainingNew){
        if(trainingNew != null){
            this.trainingService.update(id, trainingNew);
        }
    }

    @PostMapping("/api/training/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addTraining(@RequestBody Training training){
        if(training != null){
            Training result = this.trainingService.save(training);
            return Response.accepted(result.getId()).build();
        }
        System.out.println("Training in POST is null!");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/api/training/delete/{id}")
    public void deleteTraining(@PathVariable long id) {
        this.trainingService.deletebyId(id);

    }
}
