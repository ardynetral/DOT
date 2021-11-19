package com.example.simpleapplication.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonJpaRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
