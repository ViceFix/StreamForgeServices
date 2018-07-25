package com.streamforge.service.transformer;

import com.streamforge.data.dto.TwitchTokenDto;
import com.streamforge.data.entity.ExternalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableTable;

@Service
public class TransformerFactory {

    private ImmutableTable<Class, Class, AbstractTransformer<?, ?>> TRANSFORMERS;

    @Autowired
    public TransformerFactory(TokenDtoToExternalSessionTransformer tokenDtoToExternalSessionTransformer) {
        //TokenDtoToExternalSessionTransformer tokenDtoToExternalSessionTransformer1 = tokenDtoToExternalSessionTransformer;
        TRANSFORMERS = ImmutableTable.<Class, Class, AbstractTransformer<?, ?>>builder()
                .put(TwitchTokenDto.class, ExternalSession.class, tokenDtoToExternalSessionTransformer)
                .build();
    }

    @SuppressWarnings("unchecked")
    public <F, T> AbstractTransformer<F, T> getTransformer(Class<F> from, Class<T> to) {
        return (AbstractTransformer<F, T>) TRANSFORMERS.get(from, to);
    }

}
