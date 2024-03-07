package com.endropioz.schoolrestapp.student.entity;

import com.endropioz.schoolrestapp.core.entity.BasePersonEntity;
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
@Table(name = "students")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE students SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Student extends BasePersonEntity {
}
