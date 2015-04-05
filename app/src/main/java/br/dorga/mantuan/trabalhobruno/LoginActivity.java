package br.dorga.mantuan.trabalhobruno;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.dorga.mantuan.trabalhobruno.classes.Usuario;
import br.dorga.mantuan.trabalhobruno.dao.UsuarioDao;


public class LoginActivity extends ActionBarActivity {

    private EditText usuario;
    private EditText senha;
    private UsuarioDao Dao;
    public static Usuario logado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Dao = new UsuarioDao(this);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void logar(View v){
        if (Dao.verificaUser(usuario.getText().toString(), senha.getText().toString()) == null){
            Toast.makeText(this, "ERRO",Toast.LENGTH_LONG).show();
        }else {
            logado = Dao.verificaUser(usuario.getText().toString(), senha.getText().toString());
            startActivity(new Intent(this, TelaPrincipalActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cadUsuario) {
            startActivity(new Intent(this, CadUsuarioActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
