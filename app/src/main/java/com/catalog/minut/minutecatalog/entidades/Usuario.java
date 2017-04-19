package com.catalog.minut.minutecatalog.entidades;

import java.io.Serializable;

/**
 * Created by robertosampaio on 15/04/17.
 */

public class Usuario implements Serializable {
    private Long id;
    private String nomeUsuario;
    private String idFacebook;
    private String idGoole;
    private String urlFotoGoogle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getIdGoole() {
        return idGoole;
    }

    public void setIdGoole(String idGoole) {
        this.idGoole = idGoole;
    }

    public String getUrlFotoGoogle() {
        return urlFotoGoogle;
    }

    public void setUrlFotoGoogle(String urlFotoGoogle) {
        this.urlFotoGoogle = urlFotoGoogle;
    }
}
