package com.streamforge.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateService.class);

    public long getDurationInSeconds(Date date1, Date date2) {
        long diff = (date1.getTime() - date2.getTime()) / 1000;
        return Math.abs(diff);
    }
}
