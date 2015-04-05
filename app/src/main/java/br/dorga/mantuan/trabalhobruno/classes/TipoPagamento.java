package br.dorga.mantuan.trabalhobruno.classes;

import java.io.Serializable;

/**
 * Created by Dorga on 02/04/2015.
 */
public class TipoPagamento implements Serializable {
    private Long _id;
    private String modo_pagamento;

    public TipoPagamento(Long _id, String modo_pagamento) {
        this._id = _id;
        this.modo_pagamento = modo_pagamento;
    }

    public TipoPagamento(String modo_pagamento) {

        this.modo_pagamento = modo_pagamento;
    }

    public TipoPagamento() {
    }

    public String getModo_pagamento() {
        return modo_pagamento;
    }

    public void setModo_pagamento(String modo_pagamento) {
        this.modo_pagamento = modo_pagamento;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    @Override
    public String toString(){
        return this.modo_pagamento;
    }
}


