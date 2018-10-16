package demo.run.run.api;

import demo.run.run.entities.Run;
import demo.run.run.services.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class RunEndpoint {
    @Autowired
    RunService runService;

    @GetMapping("/api/run/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Run> showAll() {
        Iterable<Run> runs = this.runService.giveAll();
        return runs;
    }
    //all runs sorted by date descending
    @GetMapping("/api/run/allsortbydate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Run> showAllSorted() {
        List<Run> runs = this.runService.giveAllSorted();
        return runs;
    }
    //all unfinished runs, sorted by date ascending
    @GetMapping("/api/run/unfinished")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Run> showUnfinished() {
       return this.runService.giveUnfinished();
    }

    @GetMapping("/api/run/overdue")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateOverdue() {
        this.runService.updateOverdue();
    }


    @GetMapping("/api/run/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Run findRunById(@PathVariable long id) {
        Run run = this.runService.findById(id);
        return run;
    }

    @PostMapping("/api/run/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addRun(@RequestBody Run run){
        if(run != null){
            Run result = this.runService.save(run);
            return Response.accepted(result.getId()).build();
        }
        System.out.println("Run in POST is null!");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/api/run/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRun(@PathVariable long id, @RequestBody Run runNew){
        if(this.runService.existsById(id)) {
            this.runService.updateRun(id, runNew);
        }

    }

    @PutMapping("/api/run/finishrun/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void FinishRun(@PathVariable long id, @RequestBody Run runNew){
        if(this.runService.existsById(id)) {
            this.runService.finishRun(id, runNew);
        }
    }

    @PutMapping("/api/run/updatefinished/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFinishedRun(@PathVariable long id, @RequestBody Run runNew) {
        if (this.runService.existsById(id)) {
            //TODO
        }
    }

    @DeleteMapping("/api/run/delete/{id}")
    public Response deleteRun(@PathVariable long id) {
        if(this.runService.existsById(id)) {
            this.runService.deleteById(id);
            System.out.println("Deleted run entry with id: " + id);
            return Response.noContent().build();
        }
        System.out.println("run id in DELETE not found!");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
