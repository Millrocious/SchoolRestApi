package com.endropioz.schoolrestapp.lesson.entity;

import com.endropioz.schoolrestapp.core.entity.AuditableEntity;
import com.endropioz.schoolrestapp.module.entity.Module;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name = "lessons")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE lessons SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Lesson extends AuditableEntity {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    ZonedDateTime date;

    @Column(nullable = false)
    Integer period;

    String homework;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    Module module;

    boolean deleted = Boolean.FALSE;
}
