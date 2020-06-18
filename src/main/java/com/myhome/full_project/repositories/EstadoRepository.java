package com.myhome.full_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myhome.full_project.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
