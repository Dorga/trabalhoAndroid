package br.dorga.mantuan.trabalhobruno.classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Calendar;

/**
 * Created by Dorga on 02/04/2015.
 */
public class Dispesa implements Serializable {

    private Long _id;
    private String descricao;
    private Calendar data;
    private Double gasto;
    private Long usuario_id;
    private Long tp_pagamento;


    public Dispesa() {
    }

    public Dispesa(Long _id, String descricao, Calendar data, Double gasto, Long usuario_id, Long tp_pagamento) {
        this._id = _id;
        this.descricao = descricao;
        this.data = data;
        this.gasto = gasto;
        this.usuario_id = usuario_id;
        this.tp_pagamento = tp_pagamento;
    }

    public Dispesa( String descricao, Calendar data, Double gasto, Long usuario_id, Long tp_pagamento) {
        this.descricao = descricao;
        this.data = data;
        this.gasto = gasto;
        this.usuario_id = usuario_id;
        this.tp_pagamento = tp_pagamento;
    }

    public Dispesa(Long _id, String descricao, Double gasto) {
        this._id = _id;
        this.descricao = descricao;
        this.gasto = gasto;

    }


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Long getTp_pagamento() {
        return tp_pagamento;
    }

    public void setTp_pagamento(Long tp_pagamento) {
        this.tp_pagamento = tp_pagamento;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }
}
