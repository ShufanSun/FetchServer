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


@RestController
public class PointController {

    @Autowired
    private PointService pointService;
    private PointServiceImpl pointServiceImpl;
    @PostMapping("/add")
    public Points postPoints(@RequestBody Points points){
//
        return  pointService.savePoints(points);
    }

@PostMapping("/spend")
@ResponseBody
public ResponseEntity<?> spendPoints(@RequestBody Map<String, Integer> requestBody) {
    try {
        int pointsToSpend = requestBody.get("points");
        List<SpentResponse> updatedPoints = pointService.updatePoints(pointsToSpend);
        return ResponseEntity.ok(updatedPoints);
    } catch (IllegalArgumentException e) {
        String errorMessage="User doesn't have enough points";
        return ResponseEntity.badRequest().body(errorMessage);
    }
}

@GetMapping("/balance")
    public Map<String, Integer> getPointBalance(){

        return pointService.getPointsBalance();
}
}
