package org.ShufanServer.model;

public class BalanceResponse {
    private String payer;
    private int points;

    public BalanceResponse() {
        // No-argument constructor
    }

    public BalanceResponse(String payer, int points) {
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
