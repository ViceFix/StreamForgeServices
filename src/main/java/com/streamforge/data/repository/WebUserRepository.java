package com.streamforge.data.repository;

import com.streamforge.data.entity.WebUser;
import com.streamforge.data.repository.custom.WebUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebUserRepository extends JpaRepository<WebUser, Long>, WebUserRepositoryCustom {
}
