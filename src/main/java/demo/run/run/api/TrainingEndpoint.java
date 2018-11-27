package demo.run.run.api;

import demo.run.run.ResponseObjects.TrainingDetailResponseObject;
import demo.run.run.exception.EntityNotFoundException;
import demo.run.run.services.RunService;
import demo.run.run.entities.Training;
import demo.run.run.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class TrainingEndpoint {
    @Autowired
    TrainingService trainingService;

    @Autowired
    RunService runService;

    @GetMapping("/api/training/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Iterable<Training>> showAll() {
        Iterable<Training> trainings = this.trainingService.giveAll();
        return new ResponseEntity(trainings, HttpStatus.OK);
    }
    @GetMapping("/api/training/alltest")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Iterable<TrainingDetailResponseObject>> showAllTest() {
        Iterable<TrainingDetailResponseObject> trainingDetailResponseObjects = this.trainingService.giveAllDetails();
        return new ResponseEntity(trainingDetailResponseObjects, HttpStatus.OK);
    }
    @GetMapping("/api/training/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Training> findTrainingById(@PathVariable long id) {
        if (this.trainingService.trainingExists(id)) {
            Training training = this.trainingService.findById(id);
            return new ResponseEntity(training, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Training not found.");
        }
    }

    @GetMapping("/api/training/runs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<TrainingDetailResponseObject> findRunsinTraining(@PathVariable long id) {
        if (this.trainingService.trainingExists(id)) {
            TrainingDetailResponseObject details = this.trainingService.findTrainingdetails(id);
            return new ResponseEntity(details, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Training not found.");
        }
    }


    @PutMapping("/api/training/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity updateTraining(@PathVariable long id, @RequestBody Training trainingNew) {
        if (trainingNew != null && this.trainingService.trainingExists(trainingNew.getId())) {
            this.trainingService.update(id, trainingNew);
            return new ResponseEntity("training updated.", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Training not found, or no training given.");
        }
    }

    @PostMapping("/api/training/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public ResponseEntity addTraining(@RequestBody Training training) {
        if (training != null) {
            Training result = this.trainingService.save(training);
            return new ResponseEntity("Training added successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Could not add training.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/api/training/delete/{id}")
    public ResponseEntity deleteTraining(@PathVariable long id) {
        if (this.trainingService.trainingExists(id)) {
            this.trainingService.deleteById(id);
            return new ResponseEntity("Training deleted.", HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Training doesn't exist.");
        }
    }
}
