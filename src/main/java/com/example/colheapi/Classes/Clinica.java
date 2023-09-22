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

    @Column(name = "descricao", length = 300)
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

    public Long getCodClinica() {
        return codClinica;
    }

    public void setCodClinica(Long codClinica) {
        this.codClinica = codClinica;
    }

    public String getNmClinica() {
        return nmClinica;
    }

    public void setNmClinica(String nmClinica) {
        this.nmClinica = nmClinica;
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

    public Clinica(Long codClinica, String nmClinica, String descricao, String imagem, String bairro, String cidade, String nmEstado, String sgEstado, Boolean patrocinada) {
        this.codClinica = codClinica;
        this.nmClinica = nmClinica;
        this.descricao = descricao;
        this.imagem = imagem;
        this.bairro = bairro;
        this.cidade = cidade;
        this.nmEstado = nmEstado;
        this.sgEstado = sgEstado;
        this.patrocinada = patrocinada;
    }

    public Clinica(){

    }
}