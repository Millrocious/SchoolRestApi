package com.endropioz.schoolrestapp.parent.entity;

import com.endropioz.schoolrestapp.core.entity.BasePersonEntity;
import com.endropioz.schoolrestapp.student.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "parents")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE parents SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Parent extends BasePersonEntity {
    @OneToMany
    List<Student> children;
}
