package com.endropioz.schoolrestapp.student.repository;

import com.endropioz.schoolrestapp.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT student FROM Student student WHERE student.id IN :ids")
    List<Student> findByIds(@Param("ids") List<Long> childrenIds);

    @Query("SELECT student " +
            "FROM Student student " +
            "WHERE student.id " +
            "IN (SELECT s.id FROM ClassGroup cg JOIN cg.students s WHERE cg.id = :classGroupId)")
    List<Student> findAllByClassGroupId(@Param("classGroupId") Long classGroupId);

    boolean existsByEmailAndDeletedIsFalse(String email);
}
