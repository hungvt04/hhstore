package com.datn.be.repository;

import com.datn.be.entity.Sole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoleRepository extends JpaRepository<Sole, String> {
}
