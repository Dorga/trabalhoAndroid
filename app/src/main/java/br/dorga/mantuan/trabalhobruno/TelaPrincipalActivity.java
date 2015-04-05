package br.dorga.mantuan.trabalhobruno;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dorga.mantuan.trabalhobruno.classes.Dispesa;
import br.dorga.mantuan.trabalhobruno.dao.DispesaDao;


public class TelaPrincipalActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    TextView gastosmes;
    TextView gastopassado;
    DispesaDao daod;
    ListView lv;
    private List<Map<String, Object>> dispesas;
    Dispesa dispesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        gastosmes = (TextView) findViewById(R.id.gastosmes);
        gastopassado = (TextView) findViewById(R.id.gastopassado);
        daod = new DispesaDao(this);
        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gastosmes.setText("Gasto Total Este Mês: "+daod.gasto());
        gastopassado.setText("Gasto Total Mês Passado:"+daod.gasto_passado());
        String[] de = {"valor","descricao","modo_pagamento"};
        int[] para = {R.id.valor,R.id.descricao,R.id.modo_pagamento};
        SimpleAdapter adapter = new SimpleAdapter(this, dispesa(daod.lista()),R.layout.lista_dispesa, de, para);
        lv.setAdapter(adapter);
    }

    public List<Map<String, Object>> dispesa(Cursor c){
        dispesas = new ArrayList<Map<String, Object>>();
        Map<String, Object> i;
        while (c.moveToNext()){
            i = new HashMap<String, Object>();
            i.put("descricao",c.getString(0));
            i.put("valor",c.getDouble(1));
            i.put("modo_pagamento",c.getString(2));
            i.put("_id",c.getLong(3));
            dispesas.add(i);
        }
        return dispesas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       switch (id){
           case R.id.exit:

               break;
           case R.id.cadDispesas:
               startActivity(new Intent(this, CadDispesasActivity.class));
               break;
           case R.id.cadTpPagamentos:
               startActivity(new Intent(this, CadTpPagamento.class));
               break;
       }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = dispesas.get(position);
        Long id_dispesa = (Long) map.get("_id");
        dispesa = daod.getDispesaById(id_dispesa);
        Toast.makeText(this,dispesa.getDescricao(),Toast.LENGTH_SHORT).show();
        Intent altera_dispesa = new Intent(this,CadDispesasActivity.class);
        altera_dispesa.putExtra("dispesa",dispesa);
        startActivity(altera_dispesa);
    }
}
