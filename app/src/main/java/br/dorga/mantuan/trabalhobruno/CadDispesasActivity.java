package br.dorga.mantuan.trabalhobruno;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.dorga.mantuan.trabalhobruno.classes.Dispesa;
import br.dorga.mantuan.trabalhobruno.classes.TipoPagamento;
import br.dorga.mantuan.trabalhobruno.dao.DispesaDao;
import br.dorga.mantuan.trabalhobruno.dao.PagamentoDao;


public class CadDispesasActivity extends Activity{

    private EditText descricao;
    private EditText valor;
    private Spinner modo_pagamento;
    private PagamentoDao daop;
    private Dispesa dispesa;
    private Calendar data;
    private TipoPagamento pag;
    private DispesaDao daod;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_dispesas);
        descricao = (EditText) findViewById(R.id.descricao);
        valor = (EditText) findViewById(R.id.valor);
        modo_pagamento = (Spinner) findViewById(R.id.modo_pagamento);
        daop = new PagamentoDao(this);
        daod = new DispesaDao(this);
    }

    @Override
    protected void onResume() {
        ArrayAdapter<TipoPagamento> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daop.lista());
        modo_pagamento.setAdapter(adapter);
        data = Calendar.getInstance();
        btn = (Button) findViewById(R.id.btnexcluir);
        try {
            dispesa = (Dispesa) getIntent().getSerializableExtra("dispesa");
            setValues(dispesa);
            btn.setVisibility(View.VISIBLE);
        }catch (Exception e){

        }
        super.onResume();

    }

    public void setValues(Dispesa d){
        descricao.setText(d.getDescricao());
        valor.setText(d.getGasto().toString());
    }

    public void excluir(View v){
        daod.excluir(dispesa);
        startActivity(new Intent(this,TelaPrincipalActivity.class));
    }

    public void pegaTp(){
        pag = (TipoPagamento) modo_pagamento.getSelectedItem();
    }

    public void getDispesa(View v){
       String d = descricao.getText().toString();
       String vl = valor.getText().toString();
        if (d.isEmpty()){
            Toast.makeText(this, R.string.erro_nome, Toast.LENGTH_SHORT).show();
        }else if (vl.isEmpty()){
            Toast.makeText(this, R.string.erro_cadastro,Toast.LENGTH_SHORT).show();
        }else {
            if (dispesa == null) {
                pegaTp();
                dispesa = new Dispesa(d, data, Double.parseDouble(vl),LoginActivity.logado.get_id(),pag.get_id());
                cadastrarDis(v);
                startActivity(new Intent(this, TelaPrincipalActivity.class));
            }else {
                pegaTp();
                dispesa.setDescricao(d);
                dispesa.setGasto(Double.parseDouble(vl));
                dispesa.setTp_pagamento(pag.get_id());
                daod.update(dispesa, this);
            }
        }
    }

    public void cadastrarDis(View v) {
        daod.cadastrar(dispesa, this);
    }

    public List<String> lista_tps(){
        List<String> tps = new ArrayList<String>();
        for (TipoPagamento x : daop.lista()){
            tps.add(x.getModo_pagamento());
        }
        return tps;
    }


}
