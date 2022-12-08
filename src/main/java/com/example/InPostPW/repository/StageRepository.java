package com.example.InPostPW.repository;

import com.example.InPostPW.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage, Long> {

    List<Stage> findStagesByParcelId(Long id);
}
