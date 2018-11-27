package demo.run.run.ResponseObjects;

import demo.run.run.entities.Run;
import java.util.List;

public class TrainingDetailResponseObject {

    private double totalDistance = 0;
    private double totalFinishedDistance = 0;

    private double totalTime = 0;
    private double totalFinishedTime = 0;

    private int numberOfRuns;
    private int totalFinishedRuns = 0;

    private List<Run> runs;

    private long id;
    private String name;
    private String description;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalFinishedDistance() {
        return totalFinishedDistance;
    }

    public void setTotalFinishedDistance(double totalFinishedDistance) {
        this.totalFinishedDistance = totalFinishedDistance;
    }

    public double getTotalFinishedTime() {
        return totalFinishedTime;
    }

    public void setTotalFinishedTime(double totalFinishedTime) {
        this.totalFinishedTime = totalFinishedTime;
    }

    public int getTotalFinishedRuns() {
        return totalFinishedRuns;
    }

    public void setTotalFinishedRuns(int totalFinishedRuns) {
        this.totalFinishedRuns = totalFinishedRuns;
    }

    public void addFinishedRun(){
        this.totalFinishedRuns ++;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void addDistance (double distance) {
        this.totalDistance += distance;
    }

    public void addFinishedDistance (double distance) {
        this.totalFinishedDistance += distance;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
    public void addTime (double time) {
        this.totalTime += time;
    }

    public void addFinishedTime (double time) {
        this.totalFinishedTime += time;
    }

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public void setNumberOfRuns(int numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }
}
