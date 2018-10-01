package demo.run.run;
import demo.run.run.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RunService {
    @Autowired
    RunRepository runRepository;

    public Iterable<Run> giveAll(){
        return runRepository.findAll();
    }

    public Run findById(long id){
        return this.runRepository.findById(id).orElse(null);
    }

    public Run save (Run run) {
        return runRepository.save(run);
    }

    public void deletebyId(long id){
        this.runRepository.deleteById(id);
    }
}
