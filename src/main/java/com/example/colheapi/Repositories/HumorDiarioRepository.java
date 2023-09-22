package com.example.colheapi.Repositories;

import com.example.colheapi.Classes.HumorDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface HumorDiarioRepository extends JpaRepository<HumorDiario, Long> {
    boolean existsByCodUsuarioAndData(Long id, Date data);
    Optional<HumorDiario> findByCodUsuarioAndData(Long codusuario, Date data);
}
