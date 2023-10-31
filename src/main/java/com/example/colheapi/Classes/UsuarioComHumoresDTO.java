package com.example.colheapi.Classes;

import java.util.Date;
import java.util.List;

public class UsuarioComHumoresDTO {
    private Long codUsuario;
    private String nomeUsuario;
    private int saldo;
    private boolean premium;
    private String senha;
    private java.util.Date dataultimologin;
    private String email;
    private int diasconsecutivos;
    private Date datacadastro;
    private Integer codSkinPrincipal;
    private List<HumorDiario> humores;


    public UsuarioComHumoresDTO(Usuario usuario, List<HumorDiario> humores) {
        this.codUsuario = usuario.getId();
        this.nomeUsuario = usuario.getNome();
        this.saldo = usuario.getSaldo();
        this.premium = usuario.isPremium();
        this.senha = usuario.getSenha();
        this.dataultimologin = usuario.getDataultimologin();
        this.email = usuario.getEmail();
        this.diasconsecutivos = usuario.getDiasConsecutivos();
        this.datacadastro = usuario.getDataCadastro();
        this.codSkinPrincipal = usuario.getCodSkinPrincipal();
        this.humores = humores;
    }

    // Getters e setters

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<HumorDiario> getHumores() {
        return humores;
    }

    public void setHumores(List<HumorDiario> humores) {
        this.humores = humores;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public java.util.Date getDataultimologin() {
        return dataultimologin;
    }

    public void setDataultimologin(java.util.Date dataultimologin) {
        this.dataultimologin = dataultimologin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDiasconsecutivos() {
        return diasconsecutivos;
    }

    public void setDiasconsecutivos(int diasconsecutivos) {
        this.diasconsecutivos = diasconsecutivos;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Integer getCodSkinPrincipal() {
        return codSkinPrincipal;
    }

    public void setCodSkinPrincipal(Integer codSkinPrincipal) {
        this.codSkinPrincipal = codSkinPrincipal;
    }
}
