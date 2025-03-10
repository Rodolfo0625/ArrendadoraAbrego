package com.arrendadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrendadora.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface reservaRepository extends JpaRepository <Reserva, Long> {

    
}
