package com.endropioz.schoolrestapp.parent.repository;

import com.endropioz.schoolrestapp.parent.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByEmailAndDeletedIsFalse(String email);
}
