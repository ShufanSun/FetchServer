package org.ShufanServer.model;

/**
 * This class creates the response body structure that I want for the /spend request
 */
public class SpentResponse {
    private String payer;
    private int points;

    public SpentResponse() {
        // No-argument constructor
    }

    public SpentResponse(String payer, int points) {
        this.payer = payer;
        this.points = points;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}