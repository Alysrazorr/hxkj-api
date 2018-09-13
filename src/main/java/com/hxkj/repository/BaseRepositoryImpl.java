package com.hxkj.repository;

import com.hxkj.constant.DomainState;
import com.hxkj.domain.BaseDomain;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

public class BaseRepositoryImpl<T extends BaseDomain, ID extends Serializable> extends SimpleJpaRepository<T, Serializable> implements BaseRepository<T, Serializable> {

    private final EntityManager entityManager;

    BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void delete(T entity) {
        entity.setDomainState(DomainState.DELETED);
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteById(Serializable serializable) {
        delete(getOne(serializable));
    }
}
