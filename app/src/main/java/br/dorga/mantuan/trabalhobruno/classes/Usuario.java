package br.dorga.mantuan.trabalhobruno.classes;

import java.io.Serializable;

/**
 * Created by Dorga on 02/04/2015.
 */
public class Usuario implements Serializable {

    private Long _id;
    private String usuario;
    private String senha;
    private String email;

    public Usuario(Long _id, String usuario, String senha, String email) {
        this._id = _id;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public Usuario(String usuario, String senha, String email) {
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }
    public Usuario() {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
