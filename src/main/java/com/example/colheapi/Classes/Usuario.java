package com.example.colheapi.Classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuario")
    private long cod_usuario;

    @JsonProperty("nmUsuario")
    @Column(name = "nmusuario", length = 50)
    private String nm_usuario;


    @Column(name = "dataultimologin")
    private java.util.Date  dataultimologin;

    @Column(name = "saldo")
    private int saldo;

    @Column(name = "diasconsecutivos")
    private int dias_consecutivos;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "senha", length = 50)
    private String senha;

    @Column(name = "codskinprincipal")
    private Integer cod_skin_principal;

    @Column(name = "datacadastro")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date data_cadastro;

    @Column(name = "premium", columnDefinition = "BOOL DEFAULT FALSE")
    private boolean premium;

    public Usuario(long cod_usuario, String nm_usuario, int saldo, int dias_consecutivos,String email, String senha, Integer cod_skin_principal, Date data_cadastro, boolean premium) {
        this.cod_usuario = cod_usuario;
        this.nm_usuario = nm_usuario;
        this.saldo = saldo;
        this.dias_consecutivos = dias_consecutivos;
        this.email = email;
        this.senha = senha;
        this.cod_skin_principal = cod_skin_principal;
        this.data_cadastro = data_cadastro;
        this.premium = premium;
    }

    public Usuario(String nm_usuarioint,String email, String senha, Integer cod_skin_principal, Date data_cadastro) {
        this.nm_usuario = nm_usuario;
        this.email = email;
        this.senha = senha;
        this.cod_skin_principal = cod_skin_principal;
        this.data_cadastro = data_cadastro;
    }
    public Usuario(String nm_usuario, int saldo, int dias_consecutivos,String email, String senha, Integer cod_skin_principal, Date data_cadastro, boolean premium) {
        this.nm_usuario = nm_usuario;
        this.saldo = saldo;
        this.dias_consecutivos = dias_consecutivos;
        this.email = email;
        this.senha = senha;
        this.cod_skin_principal = cod_skin_principal;
        this.data_cadastro = data_cadastro;
        this.premium = premium;
    }

    public Usuario() {
    }

    public long getId() {
        return cod_usuario;
    }

    public void setId(long cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNome() {
        return nm_usuario;
    }

    public void setNome(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getDiasConsecutivos() {
        return dias_consecutivos;
    }

    public void setDiasConsecutivos(int dias_consecutivos) {
        this.dias_consecutivos = dias_consecutivos;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCodSkinPrincipal() {
        return cod_skin_principal;
    }

    public void setCodSkinPrincipal(Integer cod_skin_principal) {
        this.cod_skin_principal = cod_skin_principal;
    }

    public Date getDataCadastro() {
        return data_cadastro;
    }

    public void setDataCadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }


    public java.util.Date  getDataultimologin() {
        return dataultimologin;
    }

    public void setDataultimologin(java.util.Date dataultimologin) {
        this.dataultimologin = dataultimologin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cod_usuario=" + cod_usuario +
                ", nm_usuario='" + nm_usuario + '\'' +
                ", dataultimologin=" + dataultimologin +
                ", saldo=" + saldo +
                ", dias_consecutivos=" + dias_consecutivos +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cod_skin_principal=" + cod_skin_principal +
                ", data_cadastro=" + data_cadastro +
                ", premium=" + premium +
                '}';
    }
}