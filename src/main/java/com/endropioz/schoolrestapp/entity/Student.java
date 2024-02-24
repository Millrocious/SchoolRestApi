package com.endropioz.schoolrestapp.entity;

import com.endropioz.schoolrestapp.entity.base.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Table(name = "students")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE students SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Student extends AuditableEntity {
    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false, unique = true)
    String phone;

    boolean deleted = Boolean.FALSE;
}
