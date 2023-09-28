package org.ShufanServer.model;

import javax.persistence.*;

@Entity
@Table(name="pointsTable")
public class Points {
    //value declaration for the information of each json according to database columns
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//self-generated id
    @Column(name="id")
    private int id;

    @Column(name="payer")
    private String payer;

    @Column(name="points")
    private int points;

    @Column(name="timestamp")
    private String timestamp;

    //constructor for the object
    public Points(){

    }
    //setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


//getter methods
    public int getId() {
        return id;
    }

    public String getPayer() {
        return payer;
    }

    public int getPoints() {
        return points;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
