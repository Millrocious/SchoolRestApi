package com.endropioz.schoolrestapp.teacher.entity;

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
@Table(name = "teachers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE teachers SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
public class Teacher extends BasePersonEntity {

}
