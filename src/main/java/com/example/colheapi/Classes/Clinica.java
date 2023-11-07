package com.example.colheapi.Classes;

import jakarta.persistence.*;

@Entity
@Table(name = "Clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codclinica")
    private Long codClinica;

    @Column(name = "nmclinica", length = 50)
    private String nmClinica;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "telefone", length = 50, unique = true)
    private String telefone;

    @Column(name = "descricao", length = 125)
    private String descricao;

    @Column(name = "imagem", columnDefinition = "TEXT")
    private String imagem;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "nmestado", length = 50)
    private String nmEstado;

    @Column(name = "sgestado", length = 2)
    private String sgEstado;

    @Column(name = "patrocinada")
    private Boolean patrocinada;

    @Column(name = "nivelsatisfacao")
    private int nivelSatisfacao;

    public Long getCodClinica() {
        return codClinica;
    }

    public void setCodClinica(Long codClinica) {
        this.codClinica = codClinica;
    }

    public String getNmClinica() {
        return nmClinica;
    }

    public int getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    public void setNivelSatisfacao(int nivelsatisfacao) {
        this.nivelSatisfacao =  nivelsatisfacao;
    }

    public void setNmClinica(String nmClinica) {
        this.nmClinica = nmClinica;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public String getSgEstado() {
        return sgEstado;
    }

    public void setSgEstado(String sgEstado) {
        this.sgEstado = sgEstado;
    }

    public Boolean getPatrocinada() {
        return patrocinada;
    }

    public void setPatrocinada(Boolean patrocinada) {
        this.patrocinada = patrocinada;
    }

    public Clinica(String nmClinica, String email, String telefone, String descricao, String imagem, String bairro, String cidade, String nmEstado, String sgEstado, Boolean patrocinada, int nivelsatisfacao) {
        this.nmClinica = nmClinica;
        this.email = email;
        this.telefone = telefone;
        this.descricao = descricao;
        this.imagem = imagem;
        this.bairro = bairro;
        this.cidade = cidade;
        this.nmEstado = nmEstado;
        this.sgEstado = sgEstado;
        this.patrocinada = patrocinada;
        this.nivelSatisfacao = nivelsatisfacao;
    }

    public Clinica(){

    }
}
