package org.ShufanServer.repositories;

import org.ShufanServer.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Points entities. It extends JpaRepository
 * to provide basic CRUD operations for the Points entity.
 */
@Repository
public interface PointsRepository extends JpaRepository<Points, Integer> {
}
