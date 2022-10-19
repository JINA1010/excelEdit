package com.daily.report.excel.edit.core.jpa.repository;

import com.daily.report.excel.edit.core.jpa.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    LoginEntity findByLoginDt(String loginDt);
}
