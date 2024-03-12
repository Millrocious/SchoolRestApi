package com.endropioz.schoolrestapp.teacher.repository;

import com.endropioz.schoolrestapp.teacher.entity.TeacherSubjectClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherSubjectClassRepository extends JpaRepository<TeacherSubjectClass, Long> {
    Optional<TeacherSubjectClass> findByTeacherIdAndSubjectIdAndClassGroupId(Long teacherId, Long subjectId, Long classGroupId);
    Page<TeacherSubjectClass> findAllByTeacherId(Long teacherId, Pageable pageable);
}
