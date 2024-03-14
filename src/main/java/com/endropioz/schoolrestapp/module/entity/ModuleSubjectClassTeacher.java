package com.endropioz.schoolrestapp.module.entity;

import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import com.endropioz.schoolrestapp.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "module_subject_class_teacher",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "module_id",
                                "teacher_id",
                                "subject_id",
                                "class_group_id"
                        }
                )
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModuleSubjectClassTeacher {

    @EmbeddedId
    ModuleSubjectClassTeacherKey id;

    @ManyToOne
    @MapsId("moduleId")
    @JoinColumn(name = "module_id")
    Module module;

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