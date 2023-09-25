package com.example.colheapi.Repositories;

import com.example.colheapi.Classes.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
    List<Usuario> findbyEmailSenha(String email, String senha);

}
