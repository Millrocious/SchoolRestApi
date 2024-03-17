package com.endropioz.schoolrestapp.grade.entity;

import com.endropioz.schoolrestapp.core.entity.BaseEntity;
import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import com.endropioz.schoolrestapp.student.entity.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
@Table(name = "grades", uniqueConstraints = {
        @UniqueConstraint(name = "student_lesson_uc", columnNames = {"student_id", "lesson_id"})
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE grades SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Grade extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    Lesson lesson;

    @Range(min = 1, max = 12)
    @Column(name = "grade_value", nullable = false)
    Integer gradeValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type", nullable = false)
    GradeType gradeType;

    boolean deleted = Boolean.FALSE;
}
