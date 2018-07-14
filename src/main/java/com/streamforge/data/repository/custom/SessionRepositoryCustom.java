package com.streamforge.data.repository.custom;

import com.streamforge.data.entity.Session;

import java.util.List;

public interface SessionRepositoryCustom {

    List<Session> getByToken(String token);

    List<Session> getByUserId(long userId);
}
