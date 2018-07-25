package com.streamforge.data.repository;

import com.streamforge.data.entity.Session;
import com.streamforge.data.repository.custom.SessionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>, SessionRepositoryCustom {

}
