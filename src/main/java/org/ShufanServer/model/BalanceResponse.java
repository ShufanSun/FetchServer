package org.ShufanServer.model;

/**
 * Represents a response object for retrieving the balance of points per payer.
 * This class is used to structure the JSON response sent by the API.
 */

public class BalanceResponse {
    private String payer;
    private int points;

    /**
     * default constructor
     */
    public BalanceResponse() {
        // No-argument constructor
    }

    /**
     * Constructor to initialize the BalanceResponse object with payer and points.
     *
     * @param payer  The name of the payer.
     * @param points The points associated with the payer.
     */
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
