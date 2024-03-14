package com.endropioz.schoolrestapp.module.entity;

import com.endropioz.schoolrestapp.core.entity.AuditableEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Type;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "modules")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE modules SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Module extends AuditableEntity {
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    ZonedDateTime startDate;

    @Column(nullable = false)
    ZonedDateTime endDate;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Map<DayOfWeek, Set<Integer>> schedule;

    boolean deleted = Boolean.FALSE;
}
