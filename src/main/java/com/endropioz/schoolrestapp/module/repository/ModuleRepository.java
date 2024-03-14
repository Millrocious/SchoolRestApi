package com.endropioz.schoolrestapp.module.repository;

import com.endropioz.schoolrestapp.module.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Set;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {


    @Query("SELECT m FROM Module m WHERE m.startDate <= :endOfWeek AND m.endDate >= :startOfWeek")
    Set<Module> findModulesForNextWeek(
            @Param("startOfWeek") ZonedDateTime startOfWeek,
            @Param("endOfWeek") ZonedDateTime endOfWeek);
}
