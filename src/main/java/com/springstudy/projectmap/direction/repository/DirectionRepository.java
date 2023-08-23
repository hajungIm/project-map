package com.springstudy.projectmap.direction.repository;

import com.springstudy.projectmap.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
