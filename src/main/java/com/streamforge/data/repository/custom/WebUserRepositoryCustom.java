package com.streamforge.data.repository.custom;

import com.streamforge.data.entity.WebUser;
import org.springframework.transaction.annotation.Transactional;

public interface WebUserRepositoryCustom {

    WebUser findByUsername(String username);

    @Transactional(readOnly = true)
    WebUser findByCredentials(String username, String password);
}
