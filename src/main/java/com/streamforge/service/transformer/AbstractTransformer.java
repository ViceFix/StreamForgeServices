package com.streamforge.service.transformer;

import com.streamforge.service.common.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractTransformer<F, T> {

    @Autowired
    protected DateService dateService;

    public T transform(F from) {
        if (validate(from)) {
            return transformInternal(from);
        } else {
            return null;
        }
    }

    protected abstract T transformInternal(F from);

    protected boolean validate(F from) {
        return true;
    }
}
