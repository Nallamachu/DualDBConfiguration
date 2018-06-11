package com.spring.boot.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.h2.beans.Sample;

@Repository
public interface SampleDao extends JpaRepository<Sample, Integer> {

}