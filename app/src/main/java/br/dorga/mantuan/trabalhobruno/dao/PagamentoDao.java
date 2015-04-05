package br.dorga.mantuan.trabalhobruno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.dorga.mantuan.trabalhobruno.R;
import br.dorga.mantuan.trabalhobruno.classes.TipoPagamento;
import br.dorga.mantuan.trabalhobruno.connection.DbConnect;

/**
 * Created by Dorga on 03/04/2015.
 */
public class PagamentoDao extends DbConnect {

    public PagamentoDao(Context context) {
        super(context);
    }

    public br.dorga.mantuan.trabalhobruno.classes.TipoPagamento verifica(String modo){

       Cursor c =  getReadableDatabase().rawQuery("SELECT _id, lower(modo_pagamento) FROM tp_pagamento WHERE modo_pagamento = ?", new String[]{modo.toLowerCase()});
        if (c.moveToFirst()){
            return new br.dorga.mantuan.trabalhobruno.classes.TipoPagamento(c.getLong(0), c.getString(1));
        }else {
          return null;
        }
    }

    public void cadastrar(br.dorga.mantuan.trabalhobruno.classes.TipoPagamento tp, Context x){
        ContentValues cv = new ContentValues();
        cv.put(TipoPagamento.MODO_PAGAMENTO,tp.getModo_pagamento());
        long r = getWritableDatabase().insert(TipoPagamento.TABELA,null,cv);
        if (r != -1){
            Toast.makeText(x, R.string.sucesso_cadastro,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(x, R.string.erro_cadastro,Toast.LENGTH_SHORT).show();
        }
    }

    public List<br.dorga.mantuan.trabalhobruno.classes.TipoPagamento> lista(){
        Cursor c = getReadableDatabase().rawQuery("SELECT _id, modo_pagamento FROM tp_pagamento",null);
        List<br.dorga.mantuan.trabalhobruno.classes.TipoPagamento> pagamentos = new ArrayList<>();
        while (c.moveToNext()){
            pagamentos.add(new br.dorga.mantuan.trabalhobruno.classes.TipoPagamento(c.getLong(0),c.getString(1)));
        }
        c.close();
        return pagamentos;
    }
}
