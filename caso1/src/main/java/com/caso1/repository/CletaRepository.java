package com.caso1.repository;

import com.caso1.domain.Cleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CletaRepository extends JpaRepository<Cleta, Integer> {

}
