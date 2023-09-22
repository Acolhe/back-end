package com.example.colheapi.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "UsuarioClinica")
public class UsuarioClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codUsuarioClinica")
    private Long codUsuarioClinica;

    @JoinColumn(name = "codClinica")
    private Long clinica;

    @JoinColumn(name = "codUsuario")
    private Long usuario;

    @Column(name = "nivelSatisfacao")
    private Short nivelSatisfacao;

    @Column(name = "comentario", length = 300)
    private String comentario;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "dataAvaliacao")
    private java.util.Date dataAvaliacao;

    public Short getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    public void setNivelSatisfacao(Short nivelSatisfacao) {
        this.nivelSatisfacao = nivelSatisfacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getCodUsuarioClinica() {
        return codUsuarioClinica;
    }

    public Long getClinica() {
        return clinica;
    }

    public Long getUsuario() {
        return usuario;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public UsuarioClinica(Long codUsuarioClinica, Long clinica, Long usuario, Short nivelSatisfacao, String comentario, Date dataAvaliacao) {
        this.codUsuarioClinica = codUsuarioClinica;
        this.clinica = clinica;
        this.usuario = usuario;
        this.nivelSatisfacao = nivelSatisfacao;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }
    public UsuarioClinica(){}
}

