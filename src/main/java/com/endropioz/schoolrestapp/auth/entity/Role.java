package com.endropioz.schoolrestapp.auth.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public enum Role {
    SUPER_ADMIN,
    SCHOOL_ADMIN,
    STUDENT,
    TEACHER,
    PARENT;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
