package com.ddd.project.songnine.Business.Interfaces;

import jakarta.transaction.Transactional;

public interface EntityPersistenceServiceMethods<Request, Entity> {

    @Transactional
    public Entity saveEntity(Request request);
}
