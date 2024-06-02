package com.datn.be.repository;

import com.datn.be.entity.DiscountPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPeriodRepository extends JpaRepository<DiscountPeriod, String> {
}
