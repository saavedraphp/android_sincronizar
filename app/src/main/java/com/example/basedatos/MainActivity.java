package com.example.basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo,et_descripcion,et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_descripcion = (EditText) findViewById(R.id.txt_descripcion);
        et_precio = (EditText) findViewById(R.id.txt_precio);

    }

        public void registrar(View view)
    {
        AdminSqliteOpenHelper admin  = new AdminSqliteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase  database = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            database.insert("articulos",null,registro);
            database.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this,"La operacion se realizo con exito",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this,"Debes llenar los datos",Toast.LENGTH_SHORT).show();
        }

    } // fin registrar

    public void buscar(View view)
    {
        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty())
        {
            Cursor fila = database.rawQuery("select descripcion,precio from articulos where codigo =" + codigo,null);

            if(fila.moveToFirst())
            {
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                database.close();
            }
            else
            {
                Toast.makeText(this,"El producto no existe",Toast.LENGTH_SHORT).show();
                database.close();
            }
        }
        else
        {
            Toast.makeText(this,"Debes introducir el codigo del articulo",Toast.LENGTH_SHORT).show();
        }


    }//fin de buscar


    public void eliminar(View view) {
        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase database = admin.getWritableDatabase();
        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            int cantidad = database.delete("articulos", "codigo = " + codigo, null);
            database.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");


            if(cantidad == 1) {
                Toast.makeText(this, "La operacion se realizo con exito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La operacion se realizo con exito", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes introducir el codigo del articulo", Toast.LENGTH_SHORT).show();
        }

    }

    // modificar

    public void modificar(View view)
    {
        AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio", precio);

            int cantidad =  database.update("articulos",registro,"codigo = " + codigo,null);
            database.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if (cantidad==1)
            {
                Toast.makeText(this,"La operacion se realizo con exito",Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(this,"El articulo no existe",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }

}