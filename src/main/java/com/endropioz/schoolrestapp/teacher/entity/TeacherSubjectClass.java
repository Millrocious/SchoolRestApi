package com.endropioz.schoolrestapp.teacher.entity;

import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "teacher_subject_class",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"teacher_id", "subject_id", "class_group_id"})
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherSubjectClass {

    @EmbeddedId
    TeacherSubjectClassKey id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    Subject subject;

    @ManyToOne
    @MapsId("classGroupId")
    @JoinColumn(name = "class_group_id")
    ClassGroup classGroup;
}