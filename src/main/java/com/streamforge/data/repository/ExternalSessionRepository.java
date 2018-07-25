package com.streamforge.data.repository;

import com.streamforge.data.entity.ExternalSession;
import com.streamforge.data.repository.custom.ExternalSessionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalSessionRepository extends JpaRepository<ExternalSession, Long>, ExternalSessionRepositoryCustom {

}
