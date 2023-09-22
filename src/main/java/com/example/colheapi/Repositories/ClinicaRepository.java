package com.example.colheapi.Repositories;

import com.example.colheapi.Classes.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    Optional<Clinica> findByNmClinica(String nmClinica);
}
