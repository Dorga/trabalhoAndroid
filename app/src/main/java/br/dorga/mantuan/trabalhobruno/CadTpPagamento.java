package br.dorga.mantuan.trabalhobruno;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.dorga.mantuan.trabalhobruno.classes.TipoPagamento;
import br.dorga.mantuan.trabalhobruno.dao.PagamentoDao;


public class CadTpPagamento extends Activity {

    private EditText modo_pagamento;
    TipoPagamento tp;
    private PagamentoDao Dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_tp_pagamento);
        Dao = new PagamentoDao(this);
        modo_pagamento = (EditText) findViewById(R.id.pagamento);
    }

    public void getPagamento(View v){
        String p = modo_pagamento.getText().toString();
        if (p.isEmpty()){
            Toast.makeText(this,R.string.erro_nome,Toast.LENGTH_SHORT).show();
        }else {
            tp = new TipoPagamento(p);
            cadastrarTp(v);
        }
    }

    public void cadastrarTp(View v){
        if (Dao.verifica(tp.getModo_pagamento()) == null){
            Dao.cadastrar(tp,this);
        }else {
            Toast.makeText(this,R.string.registro_duplicado, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cad_tp_pagamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
