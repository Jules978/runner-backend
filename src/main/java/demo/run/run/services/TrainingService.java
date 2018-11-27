package demo.run.run.services;

import demo.run.run.ResponseObjects.TrainingDetailResponseObject;
import demo.run.run.entities.Run;
import demo.run.run.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    RunService runservice;

    public Iterable<Training> giveAll() {
        return trainingRepository.findAll();
    }

    public Training findById(long id) {
        return this.trainingRepository.findById(id).orElse(null);
    }

    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    public void deleteById(long id) {
        if (trainingRepository.existsById(id)) {
            List<Run> runs = this.runservice.FindByTraining(id);
            for (Run r : runs) {
                r.setTraining(null);
            }
            this.trainingRepository.deleteById(id);
        }
    }

    public void update(long id, Training trainingNew) {
        if (trainingRepository.existsById(id)) {
            Training trainingEdit = this.findById(id);
            trainingEdit.setName(trainingNew.getName());
            trainingEdit.setDescription(trainingNew.getDescription());
            trainingEdit.setType(trainingNew.getType());
            this.save(trainingEdit);
        }
    }

    public boolean trainingExists(long id) {
        return this.trainingRepository.existsById(id);
    }

    public TrainingDetailResponseObject findTrainingdetails(long id) {
        TrainingDetailResponseObject details = new TrainingDetailResponseObject();
        List<Run> runs = this.runservice.FindByTraining(id);
        Training training = this.findById(id);

        runs.stream()
                .forEach(p -> {
                    details.addDistance(p.getDistance());
                    details.addTime(p.getTime());
                });

        runs.stream()
                .filter(p -> p.getStatus().equals("finished"))
                .forEach(p -> {
                    details.addFinishedDistance(p.getDistance());
                    details.addFinishedTime(p.getTime());
                    details.addFinishedRun();
                });

        details.setNumberOfRuns(runs.size());
        details.setRuns(runs);
        details.setDescription(training.getDescription());
        details.setId(training.getId());
        details.setName(training.getName());
        details.setType(training.getType());
        return details;
    }
    public List<TrainingDetailResponseObject> giveAllDetails(){
        Iterable<Training> trainings = this.giveAll();
        List<TrainingDetailResponseObject> trainingResponseObjects = new ArrayList<>();
        for (Training t: trainings) {
            trainingResponseObjects.add(this.findTrainingdetails(t.getId()));
        }
        return trainingResponseObjects;
    }
}
