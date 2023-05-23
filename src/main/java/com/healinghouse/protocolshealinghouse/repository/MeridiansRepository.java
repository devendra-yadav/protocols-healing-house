package com.healinghouse.protocolshealinghouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healinghouse.protocolshealinghouse.entity.Meridian;

@Repository
public interface MeridiansRepository extends JpaRepository<Meridian, Integer>{

}
