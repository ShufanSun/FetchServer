package org.ShufanServer.service;

import org.ShufanServer.model.Points;
import org.ShufanServer.model.SpentResponse;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface PointService {
    public Points savePoints(Points points);

public List<SpentResponse> updatePoints(int points);
}
