package com.example.basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText et_usuario, et_clave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_usuario = (EditText) findViewById(R.id.txt_usuario);
        et_clave    =   (EditText) findViewById(R.id.txt_clave);


    }

    public void login(View view)
    {
        String usuario = et_usuario.getText().toString();
        String clave = et_clave.getText().toString();

        if(usuario.equals("saavedraphp@gmail.com") && clave.equals("123456")) {
            Intent productos = new Intent(this, MainActivity.class);
            startActivity(productos);
        }
        else
        {
            Toast.makeText(this,"Verifique los datos ingresados por favor",Toast.LENGTH_SHORT).show();
        }
    }
}