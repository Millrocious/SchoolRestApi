package com.endropioz.schoolrestapp.subject.repository;

import com.endropioz.schoolrestapp.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
