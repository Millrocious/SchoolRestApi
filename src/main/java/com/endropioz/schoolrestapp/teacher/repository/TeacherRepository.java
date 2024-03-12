package com.endropioz.schoolrestapp.teacher.repository;

import com.endropioz.schoolrestapp.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmailAndDeletedIsFalse(String email);
}
