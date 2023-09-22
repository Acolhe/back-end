package com.example.colheapi.Repositories;

import com.example.colheapi.Classes.UsuarioClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UsuarioClinicaRepository extends JpaRepository<UsuarioClinica, Long> {
    boolean existsByUsuarioAndClinica(Long id, Long id2);
}
