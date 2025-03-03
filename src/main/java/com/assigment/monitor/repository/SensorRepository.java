package com.assigment.monitor.repository;

import com.assigment.monitor.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Shmarlouski
 */
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("SELECT s FROM Sensor s WHERE LOWER(s.name) LIKE LOWER(concat('%', :query, '%')) OR LOWER(s.model) LIKE LOWER(concat('%', :query, '%'))")
    List<Sensor> searchByNameOrModel(@Param("query") String query);
}