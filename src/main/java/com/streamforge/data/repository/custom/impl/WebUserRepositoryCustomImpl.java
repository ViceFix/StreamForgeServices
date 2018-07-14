package com.streamforge.data.repository.custom.impl;

import com.streamforge.data.entity.WebUser;
import com.streamforge.data.repository.custom.WebUserRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WebUserRepositoryCustomImpl implements WebUserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public WebUser findByUsername(String username) {
        List<WebUser> users = entityManager
                .createNamedQuery(WebUser.SQL_FIND_BY_USERNAME, WebUser.class)
                .setParameter("username", username)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public WebUser findByCredentials(String username, String password) {
        List<WebUser> users = entityManager
                .createNamedQuery(WebUser.SQL_FIND_BY_CREDENTIALS, WebUser.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();

        return users.isEmpty() ? null : users.get(0);
    }
}
