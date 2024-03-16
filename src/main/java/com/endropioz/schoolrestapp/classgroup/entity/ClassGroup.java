package com.endropioz.schoolrestapp.classgroup.entity;

import com.endropioz.schoolrestapp.core.entity.AuditableEntity;
import com.endropioz.schoolrestapp.student.entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Set;


@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE students SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class ClassGroup extends AuditableEntity {

    @Column(unique = true, nullable = false)
    String name;

    @OneToMany
    Set<Student> students;

    boolean deleted = Boolean.FALSE;
}
