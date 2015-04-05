package br.dorga.mantuan.trabalhobruno.connection;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.dorga.mantuan.trabalhobruno.R;

/**
 * Created by Dorga on 02/04/2015.
 */
public class DbConnect extends SQLiteOpenHelper {
    private static final String BD = "APP";
    private static final int VERSAO = 1;
    public Resources res;

    public DbConnect(Context context) {
        super(context, BD, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT, senha TEXT, email TEXT);");
        db.execSQL("CREATE TABLE tp_pagamento (_id INTEGER PRIMARY KEY AUTOINCREMENT, modo_pagamento TEXT);");
        db.execSQL("CREATE TABLE dispesa (_id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT, valor DOUBLE, data DATE, usuario_id INTEGER, pagamento_id INTEGER, FOREIGN KEY(usuario_id) REFERENCES USUARIO(_id), FOREIGN KEY(pagamento_id) REFERENCES TP_PAGAMENTO(_id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuario {
        public static final String TABELA = "usuario";
        public static final String _ID = "_id";
        public static final String USUARIO = "nome";
        public static final String SENHA = "senha";
        public static final String EMAIL = "email";
        public static final String[] COLUNAS = new String[]{_ID, USUARIO, SENHA, EMAIL};

    }

    public static class TipoPagamento{
        public static final String TABELA = "tp_pagamento";
        public static final String _ID = "_id";
        public static final String MODO_PAGAMENTO = "modo_pagamento";
        public static final String[] COLUNAS = new String[]{_ID,MODO_PAGAMENTO};
    }

    public static class Dispesa{
        public static final String TABELA = "dispesa";
        public static final String _ID = "_id";
        public static final String DESCRICAO = "descricao";
        public static final String VALOR = "valor";
        public static final String DATA = "data";
        public static final String USUARIO = "usuario_id";
        public static final String PAGAMENTO = "pagamento_id";
        public static final String[] COLUNAS = new String[]{_ID, DESCRICAO, VALOR, DATA, USUARIO, PAGAMENTO};
    }
}
