package com.endropioz.schoolrestapp.lesson.repository;

import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>, CrudRepository<Lesson, Long> {
    Page<Lesson> findAllByModuleId(Long moduleId, Pageable pageable);

    @Query("SELECT l FROM Lesson l INNER JOIN ModuleSubjectClassTeacher m ON l.module.id = m.module.id WHERE m.subject.id = :subjectId")
    Page<Lesson> findAllBySubjectId(@Param("subjectId") Long subjectId, Pageable pageable);
}
