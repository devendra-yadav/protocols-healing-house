package com.healinghouse.protocolshealinghouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healinghouse.protocolshealinghouse.entity.Point;

@Repository
public interface PointsRepository extends JpaRepository<Point, String>{

}
