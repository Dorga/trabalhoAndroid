package br.dorga.mantuan.trabalhobruno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.dorga.mantuan.trabalhobruno.LoginActivity;
import br.dorga.mantuan.trabalhobruno.R;
import br.dorga.mantuan.trabalhobruno.TelaPrincipalActivity;
import br.dorga.mantuan.trabalhobruno.classes.Dispesa;
import br.dorga.mantuan.trabalhobruno.connection.DbConnect;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Dorga on 03/04/2015.
 */
public class DispesaDao extends DbConnect {
    public DispesaDao(Context context) {
        super(context);
    }

    public void cadastrar(br.dorga.mantuan.trabalhobruno.classes.Dispesa d, Context x){

        Date data = d.getData().getTime();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        ContentValues cv = new ContentValues();
        cv.put(Dispesa.DATA, dt.format(data));
        cv.put(Dispesa.DESCRICAO,d.getDescricao());
        cv.put(Dispesa.VALOR,d.getGasto().toString());
        cv.put(Dispesa.PAGAMENTO,d.getTp_pagamento());
        cv.put(Dispesa.USUARIO,d.getUsuario_id());
        long r = getWritableDatabase().insert(Dispesa.TABELA,null,cv);
        if (r != - 1){
            Toast.makeText(x, R.string.sucesso_cadastro, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(x, R.string.erro_cadastro, Toast.LENGTH_SHORT).show();
        }
    }

    public void excluir(br.dorga.mantuan.trabalhobruno.classes.Dispesa d){
        long r = getWritableDatabase().delete(Dispesa.TABELA,"_id = ?",new String[]{d.get_id().toString()});

    }

  public Cursor lista(){
     Cursor c = getReadableDatabase().rawQuery("SELECT d.descricao,d.valor,p.modo_pagamento,d._id FROM dispesa d " +
             "INNER JOIN tp_pagamento p on (p._id = d.pagamento_id) " +
             "INNER JOIN usuario u on (u._id = d.usuario_id)" +
             " WHERE u._id = ? AND d.data > (SELECT date('now','start of month'));", new String[]{LoginActivity.logado.get_id().toString()});
    return c;
  }

    public br.dorga.mantuan.trabalhobruno.classes.Dispesa getDispesaById(Long _id){
        Cursor c = getReadableDatabase().rawQuery("SELECT _id,descricao,valor FROM dispesa WHERE _id = ? ",new String[]{_id.toString()});
        if (c.moveToFirst()) {
            br.dorga.mantuan.trabalhobruno.classes.Dispesa d = new br.dorga.mantuan.trabalhobruno.classes.Dispesa(c.getLong(0), c.getString(1), c.getDouble(2));
            return d;
        }else {
            return null;
        }

    }

    public String gasto(){
        Cursor c = getReadableDatabase().rawQuery("SELECT sum(d.valor) FROM dispesa d " +
                "INNER JOIN tp_pagamento p on (p._id = d.pagamento_id) " +
                "INNER JOIN usuario u on (u._id = d.usuario_id)" +
                " WHERE u._id = ? AND d.data > (SELECT date('now','start of month'));", new String[]{LoginActivity.logado.get_id().toString()});
       c.moveToNext();
        String r = String.valueOf(c.getDouble(0));
        return r;
    }

    public String gasto_passado(){
        Cursor c = getReadableDatabase().rawQuery("SELECT sum(d.valor) FROM dispesa d INNER JOIN tp_pagamento p on (p._id = d.pagamento_id) INNER JOIN usuario u on (u._id = d.usuario_id) WHERE u._id = ? AND d.data BETWEEN (SELECT date('now','start of month','-1 month')) AND (SELECT date('now','start of month'));", new String[]{LoginActivity.logado.get_id().toString()});
        c.moveToNext();
        String r = String.valueOf(c.getDouble(0));
        return r;
    }

    public void update(br.dorga.mantuan.trabalhobruno.classes.Dispesa d, Context x){
        ContentValues cv = new ContentValues();
        cv.put(Dispesa.DESCRICAO,d.getDescricao());
        cv.put(Dispesa.VALOR,d.getGasto());
        cv.put(Dispesa.PAGAMENTO,d.getTp_pagamento());
        long r = getWritableDatabase().update(Dispesa.TABELA,cv,"_id = ?",new String[]{d.get_id().toString()});
        if (r != - 1){
            Toast.makeText(x,R.string.sucesso_alteracao,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(x,R.string.erro_alteracao,Toast.LENGTH_SHORT).show();
        }
    }

  }
