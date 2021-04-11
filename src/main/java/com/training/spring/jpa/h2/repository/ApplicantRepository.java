package com.training.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.h2.model.ApplicantInfo;

public interface ApplicantRepository extends JpaRepository<ApplicantInfo, Long> {
  List<ApplicantInfo> findByFlagged(boolean flagged);

  List<ApplicantInfo> findByNameContaining(String name);
  
  ApplicantInfo findByPassportNum(String passportNum);
  
  
}
