package org.ShufanServer.controller;

import org.ShufanServer.model.Points;
import org.ShufanServer.model.SpentResponse;
import org.ShufanServer.service.PointService;
import org.ShufanServer.service.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This class is the entry point for handling HTTP requests related to points transactions
 * Author: Shufan Sun
 */
@RestController
public class PointController {

    @Autowired
    private PointService pointService;
    private PointServiceImpl pointServiceImpl;

    // Define a POST endpoint for adding points
    @PostMapping("/add")
    public Points postPoints(@RequestBody Points points) {
// Call the service to save the points transaction
        return pointService.savePoints(points);
    }

    // Define a POST endpoint for spending points
    @PostMapping("/spend")
    @ResponseBody
    public ResponseEntity<?> spendPoints(@RequestBody Map<String, Integer> requestBody) {
        try {
            // Extract the number of points to spend from the request body
            int pointsToSpend = requestBody.get("points");
            // Call the service to spend points and get the updated points response
            List<SpentResponse> updatedPoints = pointService.updatePoints(pointsToSpend);
            // Return a 200 OK response with the updated points
            return ResponseEntity.ok(updatedPoints);
        } catch (IllegalArgumentException e) {
            // Handle the case where the user doesn't have enough points
            String errorMessage = "User doesn't have enough points";
            // Return a 400 Bad Request response with an error message
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    // Define a GET endpoint to retrieve the points balance
    @GetMapping("/balance")
    public Map<String, Integer> getPointBalance() {
// Call the service to get the points balance for the user
        return pointService.getPointsBalance();
    }
}
