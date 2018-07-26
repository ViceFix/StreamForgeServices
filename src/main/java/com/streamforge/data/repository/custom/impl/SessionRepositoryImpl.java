package com.streamforge.data.repository.custom.impl;

import com.streamforge.data.entity.Session;
import com.streamforge.data.repository.custom.SessionRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionRepositoryImpl implements SessionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Session> getByToken(String token) {
        return entityManager
                        .createNamedQuery(Session.SQL_GET_BY_TOKEN, Session.class)
                        .setParameter("token", token)
                        .getResultList();
    }

    @Override
    public List<Session> getByUserId(long userId) {
        return entityManager
                .createNamedQuery(Session.SQL_GET_BY_USER_ID, Session.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}
