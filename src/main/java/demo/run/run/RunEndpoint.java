package demo.run.run;

import demo.run.run.Run;
import demo.run.run.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class RunEndpoint {
    @Autowired
    RunService runService;

    @GetMapping("/api/run")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Run> showAll() {
        Iterable<Run> runs = this.runService.giveAll();
        return runs;
    }

    @GetMapping("/api/run/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Run findRunById(@PathVariable long id) {
        Run run = this.runService.findById(id);
        return run;
    }
}
