package org.ShufanServer.service;

import org.ShufanServer.model.Points;
import org.ShufanServer.model.SpentResponse;
import org.ShufanServer.repositories.PointsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the PointService interface that handles points-related operations.
 * Author: Shufan Sun
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointsRepository pointsRepository;

    /**
     * Save a points transaction.
     *
     * @param points The points transaction to be saved.
     * @return The saved points transaction.
     */
    @Override
    public Points savePoints(Points points) {
        Points existing = pointsRepository.findById(points.getId()).orElse(null);
        if (existing != null) {
            // If the points object has a non-null ID, it's an existing record, so update it.
            Points existingPoints = pointsRepository.findById(points.getId()).orElse(null);

            if (existingPoints != null) {
                // Update the fields of the existing record with the new values.
                existingPoints.setPayer(points.getPayer());
                existingPoints.setPoints(points.getPoints());
                existingPoints.setTimestamp(points.getTimestamp());

                // Save the updated record.
                return pointsRepository.save(existingPoints);
            } else {
                // Handle the case where the points record with the given ID doesn't exist.
                throw new IllegalArgumentException("Points record not found with ID: " + points.getId());
            }
        } else {
            // If the points object doesn't have an ID, it's a new record, so simply save it.
            return pointsRepository.save(points);
        }
    }


    /**
     * Spend points based on the specified amount.
     *
     * @param pointsToSpend The number of points user wants to spend.
     * @return A list of spent points transactions.
     */
    @Override
    public List<SpentResponse> updatePoints(@RequestParam(value = "points") int pointsToSpend) {
        //validate user input
        if (pointsToSpend <= 0) {
            throw new IllegalArgumentException("Invalid points to spend");
        }
        // Fetch all points transactions sorted by the transaction timestamp (oldest first)
        List<Points> userPoints = pointsRepository.findAll(Sort.by(Sort.Direction.ASC, "timestamp"));
        // Create a map to store the points spent by each payer
        Map<String, Integer> pointsSpentByPayer = new HashMap<>();
        // Iterate through the points transactions to spend the points
        for (Points pointsTransaction : userPoints) {
            int availablePoints = pointsTransaction.getPoints();
            String payer = pointsTransaction.getPayer();

            // Calculate how many points can be spent from this transaction
            int pointsToDeduct = Math.min(availablePoints, pointsToSpend);

            // Deduct the points from the transaction
            pointsTransaction.setPoints(availablePoints - pointsToDeduct);
            pointsRepository.save(pointsTransaction);

            // Update the points spent by the payer
            pointsSpentByPayer.put(payer, pointsSpentByPayer.getOrDefault(payer, 0) - pointsToDeduct);

            // Deduct the spent points from the total
            pointsToSpend -= pointsToDeduct;

            // If all points have been spent, break the loop
            if (pointsToSpend <= 0) {
                break;
            }
        }

        // Check if the user has enough points to spend
        if (pointsToSpend > 0) {
            throw new IllegalArgumentException("Insufficient points to spend");
        }

        // Create a list of Points objects to represent the points spent
        List<SpentResponse> pointsSpentList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : pointsSpentByPayer.entrySet()) {
            SpentResponse spentResponse = new SpentResponse(entry.getKey(), entry.getValue());
            //if there's no points left for this payer, we would only show the points deducted from other payers
            if (entry.getValue() != 0) pointsSpentList.add(spentResponse);
        }
        return pointsSpentList;
    }

    /**
     * Get the points balance per payer.
     *
     * @return A map containing the points balance per payer.
     */
    @Override
    public Map<String, Integer> getPointsBalance() {
        List<Points> userPoints = pointsRepository.findAll();
        Map<String, Integer> filtered = new HashMap<>();
        // Iterate through the points transactions to spend the points
        for (Points balance : userPoints) {
            int points = balance.getPoints();
            String payer = balance.getPayer();

            // Update the points spent by the payer
            filtered.put(payer, filtered.getOrDefault(payer, 0) + points);

        }

        return filtered;
    }


}
