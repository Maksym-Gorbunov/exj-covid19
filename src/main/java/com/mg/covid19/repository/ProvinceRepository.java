package com.mg.covid19.repository;

import com.mg.covid19.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
}
