package br.dorga.mantuan.trabalhobruno;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.dorga.mantuan.trabalhobruno.classes.Usuario;
import br.dorga.mantuan.trabalhobruno.dao.UsuarioDao;


public class CadUsuarioActivity extends Activity {

    private EditText nome;
    private EditText senha;
    private EditText email;
    Usuario user;
    UsuarioDao Dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        nome = (EditText) findViewById(R.id.nome);
        senha = (EditText) findViewById(R.id.senha);
        email = (EditText) findViewById(R.id.email);
        Dao = new UsuarioDao(this);

    }

    public void getUser(View v) {
        String n = nome.getText().toString();
        String s = senha.getText().toString();
        String e = email.getText().toString();

        if (n.isEmpty()){
            Toast.makeText(this,R.string.erro_nome,Toast.LENGTH_SHORT).show();
        }else if(s.isEmpty()){
            Toast.makeText(this,R.string.erro_senha,Toast.LENGTH_SHORT).show();
        }else if (e.isEmpty() || !e.contains("@")){
            Toast.makeText(this,R.string.erro_email,Toast.LENGTH_SHORT).show();
        }else {
            user = new Usuario(n,s,e);
            cadastrar(v);
        }
    }


    public void cadastrar(View v) {
                if (Dao.verificaUser(user.getUsuario(), user.getSenha()) == null) {
                Dao.cadastrar(user, this);
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                Toast.makeText(this, R.string.registro_duplicado, Toast.LENGTH_SHORT).show();
            }
        }

}
