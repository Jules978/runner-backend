package demo.run.run.services;

import demo.run.run.entities.Run;
import demo.run.run.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    RunService runservice;

    public Iterable<Training> giveAll(){
        return trainingRepository.findAll();
    }

    public Training findById(long id){
        return this.trainingRepository.findById(id).orElse(null);
    }

    public Training save (Training training) {
        return trainingRepository.save(training);
    }

    public void deletebyId(long id) {
        if (trainingRepository.existsById(id)) {
            List<Run> runs = this.runservice.FindByTraining(id);
            for (Run r : runs) {
                r.setTraining(null);
            }
            this.trainingRepository.deleteById(id);
        }
    }

    public void update(long id, Training trainingNew){
        if(trainingRepository.existsById(id)) {

            Training trainingEdit = this.findById(id);
            trainingEdit.setName(trainingNew.getName());
            trainingEdit.setDescription(trainingNew.getDescription());
            trainingEdit.setType(trainingNew.getType());

            this.save(trainingEdit);
        }
    }
}
