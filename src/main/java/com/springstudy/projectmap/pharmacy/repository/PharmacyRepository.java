package com.springstudy.projectmap.pharmacy.repository;

import com.springstudy.projectmap.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
