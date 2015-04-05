package br.dorga.mantuan.trabalhobruno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.widget.Toast;

import br.dorga.mantuan.trabalhobruno.LoginActivity;
import br.dorga.mantuan.trabalhobruno.R;
import br.dorga.mantuan.trabalhobruno.classes.*;
import br.dorga.mantuan.trabalhobruno.classes.Usuario;
import br.dorga.mantuan.trabalhobruno.connection.DbConnect;

import static android.support.v4.app.ActivityCompat.startActivity;
import static br.dorga.mantuan.trabalhobruno.connection.DbConnect.*;
import static br.dorga.mantuan.trabalhobruno.connection.DbConnect.Usuario.*;

/**
 * Created by Dorga on 02/04/2015.
 */
public class UsuarioDao extends DbConnect{



    public UsuarioDao(Context context){
      super(context);
    }


    public br.dorga.mantuan.trabalhobruno.classes.Usuario verificaUser(String nome, String senha){
        Cursor c = getReadableDatabase().rawQuery("SELECT _id, lower(nome), senha, email FROM usuario WHERE nome = ? and senha = ?", new String[]{nome.toLowerCase(),senha});
      if (c.moveToFirst()){
         return new br.dorga.mantuan.trabalhobruno.classes.Usuario(c.getLong(0),c.getString(1),c.getString(2),c.getString(3));
      }else {
          return null;
      }
    }


    public void cadastrar(br.dorga.mantuan.trabalhobruno.classes.Usuario u, Context c){
        ContentValues cv = new ContentValues();
        cv.put(USUARIO,u.getUsuario());
        cv.put(SENHA,u.getSenha());
        cv.put(EMAIL, u.getEmail());
        long resultado = getWritableDatabase().insert(TABELA, null, cv);
        if (resultado != -1){
            Toast.makeText(c, (R.string.sucesso_cadastro),Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(c, (R.string.erro_cadastro),Toast.LENGTH_LONG).show();
        }
    }

    public void deslogar(br.dorga.mantuan.trabalhobruno.classes.Usuario u, Context c){
        u = null;

    }




}
