package demo.run.run.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="runs")
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    Date date;
    private double distance;
    private String title;
    private Double time;
    private Double avspeed;
    private Integer score;
    private String overdue;
    private String finished;
    private String comment;
    @ManyToOne
    private Training training;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Double getAvspeed() {
        return avspeed;
    }

    public void setAvspeed(Double avspeed) {
        this.avspeed = avspeed;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }


    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public boolean checkTimeAndDistance(){
        if (this.time != null && this.distance > 0.0){
            return true;
        } else {
            return false;
        }
    }

}
