package com.endropioz.schoolrestapp.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public abstract class BasePersonEntity extends AuditableEntity {
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
