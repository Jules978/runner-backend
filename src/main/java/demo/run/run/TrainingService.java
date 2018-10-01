package demo.run.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    public Iterable<Training> giveAll(){
        return trainingRepository.findAll();
    }

    public Training findById(long id){
        return this.trainingRepository.findById(id).orElse(null);
    }

    public Training save (Training training) {
        return trainingRepository.save(training);
    }

    public void deletebyId(long id){
        this.trainingRepository.deleteById(id);
    }
}
