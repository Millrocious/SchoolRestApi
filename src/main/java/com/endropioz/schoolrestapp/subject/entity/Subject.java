package com.endropioz.schoolrestapp.subject.entity;

import com.endropioz.schoolrestapp.core.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Table(name = "subjects")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE subjects SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Subject extends AuditableEntity {
    @Column(nullable = false, unique = true)
    String name;

    boolean deleted = Boolean.FALSE;
}
