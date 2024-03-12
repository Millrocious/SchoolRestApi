package com.endropioz.schoolrestapp.classgroup.repository;

import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
}
