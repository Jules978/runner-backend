package demo.run.run.services;

import demo.run.run.entities.Run;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class RunService {
    @Autowired
    RunRepository runRepository;

    public Iterable<Run> giveAll(){
        return this.runRepository.findAll();
    }

    public List<Run> giveAllSorted(){
        Iterable<Run> tempRun = this.runRepository.findAll();
        List<Run> runs = new ArrayList<>();
        for (Run r: tempRun){
            runs.add(r);
        }
        Collections.sort(runs, new Comparator<Run>() {
            @Override
            public int compare(Run o1, Run o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return runs;
    }

    public List<Run> giveUnfinished(){
        List<Run> runs = this.runRepository.findAllUnfinishedRuns();
        Collections.sort(runs, new Comparator<Run>() {
            @Override
            public int compare(Run o1, Run o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return runs;
    }

    public Run findById(long id){
        return this.runRepository.findById(id).orElse(null);
    }

    public Run save (Run run) {
        return runRepository.save(run);
    }

    public void deleteById(long id){
        this.runRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return this.runRepository.existsById(id);
    }

    public void updateUnfinishedRun(long id, Run runNew){
        Run runEdit = this.findById(id);
        runEdit.setTitle(runNew.getTitle());
        runEdit.setDistance(runNew.getDistance());
        runEdit.setComment(runNew.getComment());
        runEdit.setTraining(runNew.getTraining());
        this.save(runEdit);
    }

    public void finishRun(long id, Run runNew){
        Run runEdit = this.findById(id);
        runEdit.setDistance(runNew.getDistance());
        if(runNew.checkTimeAndDistance()) {
            runEdit.setTime(runNew.getTime());
            double pace = runNew.getTime() / runNew.getDistance();
            runEdit.setAvspeed(pace);
        }
        runEdit.setComment(runNew.getComment());
        runEdit.setTitle(runNew.getTitle());
        runEdit.setFinished("yes");
        runEdit.setScore(runNew.getScore());
        this.save(runEdit);
    }

    public void updateOverdue() {
        Date date = new Date();

        List<Run> runs = this.runRepository.findAllNotOverdueRuns();
        for (Run r : runs) {
            Date runDate = r.getDate();
            if (runDate.before(date) && r.getFinished().equals("no")) {

                r.setOverdue("overdueRun");
            } else {
                r.setOverdue("no");
            }
        }
    }

    public List<Run> FindByTraining(long id){
        return this.runRepository.FindRunsByTraining(id);
    }
}
