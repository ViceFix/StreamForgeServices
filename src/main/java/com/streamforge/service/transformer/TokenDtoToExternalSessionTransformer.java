package com.streamforge.service.transformer;

import com.streamforge.data.dto.TwitchTokenDto;
import com.streamforge.data.entity.ExternalSession;
import com.streamforge.service.common.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenDtoToExternalSessionTransformer extends AbstractTransformer<TwitchTokenDto, ExternalSession> {

    @Override
    protected ExternalSession transformInternal(TwitchTokenDto from) {
        Date expirationDate = dateService.getSumDateFromNow(from.getExpiration(), Calendar.SECOND);

        return new ExternalSession.ExternalSessionBuilder()
                .setAccessToken(from.getAccessToken())
                .setRefreshToken(from.getRefreshToken())
                .setExpirationDate(expirationDate)
                .build();
    }

}
