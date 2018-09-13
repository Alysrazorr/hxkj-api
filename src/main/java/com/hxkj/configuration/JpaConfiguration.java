package com.hxkj.configuration;

import com.hxkj.repository.BaseRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @author dsd
 * @version 2018/7/2 17:21
 */
@SuppressWarnings("all")
@Configuration
@EnableSpringDataWebSupport
@EnableJpaRepositories(basePackages = "com.hxkj.**.repository", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaConfiguration {
}
