package com.streamforge.data.repository.custom.impl;

import com.streamforge.data.entity.ExternalSession;
import com.streamforge.data.repository.custom.ExternalSessionRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ExternalSessionRepositoryCustomImpl implements ExternalSessionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ExternalSession findByUserId(Long userId) {
        List<ExternalSession> externalSessions = entityManager.createNamedQuery(ExternalSession.SQL_GET_BY_USER_ID, ExternalSession.class)
                .setParameter("userId", userId)
                .getResultList();

        return externalSessions.isEmpty() ? null : externalSessions.get(0);
    }
}
