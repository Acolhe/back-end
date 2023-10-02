package com.example.colheapi.Classes;

import com.example.colheapi.Classes.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "humor")
public class HumorDiario {
    @Column(name = "codusuario")
    private Long codUsuario;

    @Id
    @Column(name = "codhumor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codhumor;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "data")
    private Date data;

    @Column(name = "nivelsatisfacao")
    private Integer nivelSatisfacao;

    @Column(name = "comentario", length = 300)
    private String comentario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "codusuario", referencedColumnName = "codusuario", insertable = false, updatable = false)
    private Usuario usuario;

    // Getters e setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    public void setNivelSatisfacao(Integer nivelSatisfacao) {
        this.nivelSatisfacao = nivelSatisfacao;
    }

    public String getComentario() {
        return comentario;
    }

    public Long getCodhumor() {
        return codhumor;
    }

    public void setCodhumor(Long codhumor) {
        this.codhumor = codhumor;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public HumorDiario(Long codUsuario, Long codhumor, Date data, Integer nivelSatisfacao, String comentario, Usuario usuario) {
        this.codUsuario = codUsuario;
        this.codhumor = codhumor;
        this.data = data;
        this.nivelSatisfacao = nivelSatisfacao;
        this.comentario = comentario;
        this.usuario = usuario;
    }
    public HumorDiario(){

    }

    public void setFormattedData(String dataStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.data = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            // Lidar com erros de formatação de data, se necessário
            e.printStackTrace();
        }
    }

}
