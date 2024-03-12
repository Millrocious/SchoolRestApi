package com.endropioz.schoolrestapp.teacher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherSubjectClassKey implements Serializable {
    @Column(name = "teacher_id")
    Long teacherId;

    @Column(name = "subject_id")
    Long subjectId;

    @Column(name = "class_group_id")
    Long classGroupId;
}