package com.endropioz.schoolrestapp.attendance.entity;

import com.endropioz.schoolrestapp.core.entity.AuditableEntity;
import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import com.endropioz.schoolrestapp.student.entity.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Table(name = "attendances",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "lesson_student_uc",
                        columnNames = {"lesson_id", "student_id"}
                )
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE attendances SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Attendance extends AuditableEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    AttendanceType attendanceType;

    @Column(nullable = false)
    boolean deleted = Boolean.FALSE;
}
