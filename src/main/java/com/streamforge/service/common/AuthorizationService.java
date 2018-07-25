package com.streamforge.service.common;

import com.streamforge.data.repository.SessionRepository;
import com.streamforge.data.entity.Session;
import com.streamforge.data.entity.WebUser;
import com.streamforge.data.repository.WebUserRepository;
import com.streamforge.service.common.security.TokenService;
import com.streamforge.service.exception.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthorizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    private final TokenService tokenService;
    private final SessionRepository sessionRepository;
    private final WebUserRepository webUserRepository;
    private final DateService dateService;

    @Autowired
    public AuthorizationService(
            TokenService tokenService,
            SessionRepository sessionRepository,
            WebUserRepository webUserRepository,
            DateService dateService) {
        this.tokenService = tokenService;
        this.sessionRepository = sessionRepository;
        this.webUserRepository = webUserRepository;
        this.dateService = dateService;
    }

    public Session createSession(WebUser user) {
        List<Session> sessions = sessionRepository.getByUserId(user.getUserId());
        if (!sessions.isEmpty()) {
            return sessions.get(0);
        }
        Session session = generateSession(user);
        saveSession(session);
        return session;
    }

    public String transformToken(String token) {
        return token.split(" ")[1];
    }

    public Session refreshSession(String token) {
        Session session = Optional.ofNullable(findSession(token))
                .orElseThrow(() -> new AuthorizationException("Token is not found: " + token));
        WebUser user = webUserRepository.findById(session.getUserId())
                .orElseThrow(() -> new AuthorizationException("User from the session was not found"));

        String refreshedToken = tokenService.generateCustomToken(user);
        session.setToken(refreshedToken);
        session.setLastAuthDate(new Date());
        session.setState(Session.State.ACTIVE);
        saveSession(session);
        return session;
    }

    private void saveSession(Session session) {
        sessionRepository.saveAndFlush(session);
    }

    public Session findSession(String token) {
        List<Session> sessions = sessionRepository.getByToken(token);
        if (sessions.isEmpty()) {
            return null;
        }

        Session session = sessions.get(0);
        setSessionState(session);
        return session;
    }

    private Session generateSession(WebUser webUser) {
        long userId = Optional.ofNullable(webUser)
                .map(WebUser::getUserId)
                .orElseThrow(() -> new AuthorizationException("Unable to create session for undefined user"));

        String token = tokenService.generateCustomToken(webUser);

        Session newSession =
                new Session.SessionBuilder()
                        .setUserId(userId)
                        .setToken(token)
                        .build();

        LOGGER.debug("Session is created for user id {}", webUser.getUserId());
        return newSession;
    }

    private void setSessionState(Session session) {
        long period = dateService.getDurationInSeconds(new Date(), session.getLastAuthDate());
        session.setState(period < Constants.TOKEN_EXPIRATION_TIME ? Session.State.ACTIVE : Session.State.EXPIRED);
    }
}
