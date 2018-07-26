package com.streamforge.data.repository.custom;

import com.streamforge.data.entity.ExternalSession;

public interface ExternalSessionRepositoryCustom {

    ExternalSession findByUserId(Long userId);

}
