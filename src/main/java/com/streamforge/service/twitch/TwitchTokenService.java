package com.streamforge.service.twitch;

import com.streamforge.data.dto.TwitchTokenDto;
import com.streamforge.data.dto.twitch.UserDataNativeDto;
import com.streamforge.data.entity.ExternalSession;
import com.streamforge.data.entity.WebUser;
import com.streamforge.data.repository.ExternalSessionRepository;
import com.streamforge.data.repository.WebUserRepository;
import com.streamforge.service.common.DateService;
import com.streamforge.service.common.security.TokenService;
import com.streamforge.service.transformer.AbstractTransformer;
import com.streamforge.service.transformer.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.function.BiFunction;

@Service
public class TwitchTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitchTokenService.class);

    private final TransformerFactory transformerFactory;
    private final TwitchUserDataService twitchUserDataService;
    private final TokenService tokenService;
    private final WebUserRepository webUserRepository;
    private final ExternalSessionRepository externalSessionRepository;
    private final DateService dateService;

    @Autowired
    public TwitchTokenService(TransformerFactory transformerFactory,
                              TwitchUserDataService twitchUserDataService,
                              TokenService tokenService,
                              WebUserRepository webUserRepository,
                              ExternalSessionRepository externalSessionRepository,
                              DateService dateService) {
        this.transformerFactory = transformerFactory;
        this.twitchUserDataService = twitchUserDataService;
        this.tokenService = tokenService;
        this.webUserRepository = webUserRepository;
        this.externalSessionRepository = externalSessionRepository;
        this.dateService = dateService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String saveOrUpdateTwitchSession(TwitchTokenDto tokenDto) {
        UserDataNativeDto userDto = twitchUserDataService.getUserDataByToken(tokenDto.getAccessToken());
        WebUser authorizedUser = webUserRepository.findByUsername(userDto.getName());

        return authorizedUser == null
                ? processExternalSession(this::processSessionForNewUser, tokenDto, createWebUser(userDto))
                : processExternalSession(this::processSessionForExistingUser, tokenDto, authorizedUser);
    }

    private ExternalSession processSessionForNewUser(TwitchTokenDto tokenDto, WebUser user) {
        AbstractTransformer<TwitchTokenDto, ExternalSession> transformer =
                transformerFactory.getTransformer(TwitchTokenDto.class, ExternalSession.class);

        ExternalSession externalSession = transformer.transform(tokenDto);
        externalSession.setUserId(user.getUserId());
        return externalSession;
    }

    private ExternalSession processSessionForExistingUser(TwitchTokenDto tokenDto, WebUser user) {
        ExternalSession externalSession = externalSessionRepository.findByUserId(user.getUserId());
        Date expirationDate = dateService.getSumDateFromNow(tokenDto.getExpiration(), Calendar.SECOND);

        externalSession.setAccessToken(tokenDto.getAccessToken());
        externalSession.setRefreshToken(tokenDto.getRefreshToken());
        externalSession.setExpirationDate(expirationDate);
        return externalSession;
    }

    private String processExternalSession(BiFunction<TwitchTokenDto, WebUser, ExternalSession> sessionProvider,
                                          TwitchTokenDto tokenDto,
                                          WebUser user) {
        ExternalSession externalSession = sessionProvider.apply(tokenDto, user);
        externalSession.setServiceToken(tokenService.generateCustomToken(user));
        externalSessionRepository.saveAndFlush(externalSession);
        return externalSession.getServiceToken();
    }

    private WebUser createWebUser(UserDataNativeDto userData) {
        LOGGER.info("Creating new WebUser. Username: {}", userData.getName());
        WebUser user = new WebUser();
        user.setUsername(userData.getName());
        user.setDisplayName(userData.getDisplayName());
        user.setExternalId(userData.getId());

        webUserRepository.saveAndFlush(user);
        return webUserRepository.findByUsername(user.getUsername());
    }

}
