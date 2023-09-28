package org.ShufanServer.service;

import org.ShufanServer.model.Points;
import org.ShufanServer.model.SpentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing points and point transactions.
 * This interface defines the contract for handling points-related operations.
 * Author: Shufan Sun
 */
@Service
public interface PointService {
    /**
     * Save a points transaction.
     *
     * @param points The points transaction to be saved.
     * @return The saved points transaction.
     */
    public Points savePoints(Points points);

    /**
     * Spend points based on the specified amount.
     *
     * @param points The number of points user wants to spend.
     * @return A list of spent points transactions.
     */
    public List<SpentResponse> updatePoints(int points);

    /**
     * Get the points balance per payer.
     *
     * @return A map containing the points balance per payer.
     */
    Map<String, Integer> getPointsBalance();
}
