package com.endropioz.schoolrestapp.module.repository;

import com.endropioz.schoolrestapp.module.entity.ModuleSubjectClassTeacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleSubjectClassTeacherRepository extends JpaRepository<ModuleSubjectClassTeacher, Long> {
    Page<ModuleSubjectClassTeacher> findAllByModuleId(Long moduleId, Pageable pageable);

    Optional<ModuleSubjectClassTeacher> findByModuleIdAndSubjectIdAndClassGroupIdAndTeacherId(Long moduleId, Long subjectId, Long classId, Long teacherId);
}
