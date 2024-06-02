package com.datn.be.repository;

import com.datn.be.entity.DiscountPeriodDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPeriodDetailRepository extends JpaRepository<DiscountPeriodDetail, String> {
}
