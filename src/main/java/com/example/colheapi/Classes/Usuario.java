package com.example.colheapi.Classes;

import com.example.colheapi.Classes.HumorDiario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuario")
    private long cod_usuario;

    @JsonProperty("nmUsuario")
    @Column(name = "nm_usuario", length = 50)
    private String nm_usuario;

    @Column(name = "saldo")
    private int saldo;

    @Column(name = "dias_consecutivos")
    private int dias_consecutivos;

    @Column(name = "imagem", columnDefinition = "TEXT")
    private String imagem;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "senha", length = 50)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HumorDiario> humoresDiarios;

    @Column(name = "cod_skin_principal")
    private int cod_skin_principal;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date data_cadastro;

    @Column(name = "premium", columnDefinition = "BOOL DEFAULT FALSE")
    private boolean premium;

    public Usuario(long cod_usuario, String nm_usuario, int saldo, int dias_consecutivos, String imagem, String telefone, String email, String senha, int cod_skin_principal, Date data_cadastro, boolean premium) {
        this.cod_usuario = cod_usuario;
        this.nm_usuario = nm_usuario;
        this.saldo = saldo;
        this.dias_consecutivos = dias_consecutivos;
        this.imagem = imagem;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.cod_skin_principal = cod_skin_principal;
        this.data_cadastro = data_cadastro;
        this.premium = premium;
    }
    public Usuario(String nm_usuario, int saldo, int dias_consecutivos, String imagem, String telefone, String email, String senha, int cod_skin_principal, Date data_cadastro, boolean premium) {
        this.nm_usuario = nm_usuario;
        this.saldo = saldo;
        this.dias_consecutivos = dias_consecutivos;
        this.imagem = imagem;
        this.telefone = telefone;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public int getCodSkinPrincipal() {
        return cod_skin_principal;
    }

    public void setCodSkinPrincipal(int cod_skin_principal) {
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

    public List<HumorDiario> getHumoresDiarios() {
        return humoresDiarios;
    }

    public void setHumoresDiarios(List<HumorDiario> humoresDiarios) {
        this.humoresDiarios = humoresDiarios;
    }
}
