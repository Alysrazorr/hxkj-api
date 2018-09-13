package com.hxkj.repository;

import com.hxkj.domain.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseDomain, ID extends Serializable> extends JpaRepository<T, ID> {

}
