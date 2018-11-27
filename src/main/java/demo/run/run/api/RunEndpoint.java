package demo.run.run.api;

import demo.run.run.entities.Run;
import demo.run.run.exception.EntityNotFoundException;
import demo.run.run.services.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class RunEndpoint {
    @Autowired
    RunService runService;

    @GetMapping("/api/run/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity <Iterable<Run>> showAll() {
        Iterable<Run> runs = this.runService.giveAll();
        return new ResponseEntity(runs, HttpStatus.OK);
    }
    //all runs sorted by date descending
    @GetMapping("/api/run/allsortbydate")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity <List<Run>> showAllSorted() {
        List<Run> runs = this.runService.giveAllSorted();
        return new ResponseEntity(runs, HttpStatus.OK);
    }
    //all unfinished runs, sorted by date ascending
    @GetMapping("/api/run/unfinished")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Run>> showUnfinished() {
        List<Run> runs =  this.runService.giveUnfinished();
        return new ResponseEntity(runs, HttpStatus.OK);
    }

    @GetMapping("/api/run/overdue")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity updateOverdue() {
        this.runService.updateOverdue();
        return new ResponseEntity("overdue runs updated.", HttpStatus.OK);
    }


    @GetMapping("/api/run/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Run> findRunById(@PathVariable long id) {
        if(this.runService.existsById(id)) {
            Run run = this.runService.findById(id);
            return new ResponseEntity(run,HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("run doesn't exist.");
        }

    }

    @PostMapping("/api/run/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public ResponseEntity<Run> addRun(@RequestBody Run run){
        if(run != null){
            Run result = this.runService.save(run);
            return new ResponseEntity<Run>(result, HttpStatus.OK);
        }
        return new ResponseEntity("Run could not be added.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/api/run/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Run> updateRun(@PathVariable long id, @RequestBody Run runNew){
        if(this.runService.existsById(id)) {
            this.runService.updateRun(id, runNew);
            Run result = runService.findById(id);
            return new ResponseEntity<Run>(result, HttpStatus.OK);
        }else {
            throw new EntityNotFoundException("run doesn't exist.");
        }

    }

    @PutMapping("/api/run/finishrun/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Run> FinishRun(@PathVariable long id, @RequestBody Run runNew){
        if(this.runService.existsById(id)) {
            this.runService.finishRun(id, runNew);
            Run result = runService.findById(id);
            return new ResponseEntity<Run>(result, HttpStatus.OK);
        }else {
            throw new EntityNotFoundException("run doesn't exist.");
        }
    }

    @PutMapping("/api/run/updatefinished/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFinishedRun(@PathVariable long id, @RequestBody Run runNew) {
        if (this.runService.existsById(id)) {
            //TODO
        }else {
            throw new EntityNotFoundException("run doesn't exist.");
        }
    }

    @DeleteMapping("/api/run/delete/{id}")
    public ResponseEntity deleteRun(@PathVariable long id) {
        if(this.runService.existsById(id)) {
            this.runService.deleteById(id);
            System.out.println("Deleted run entry with id: " + id);
            return new ResponseEntity("Run deleted.", HttpStatus.OK);
        }else {
            throw new EntityNotFoundException("run doesn't exist.");
        }
    }
}
