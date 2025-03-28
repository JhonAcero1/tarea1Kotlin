package com.jhon.usosql;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    Button boton_guardar =  findViewById(R.id.btn_guardar);
    TextView texto = findViewById(R.id.input_nombre);

        MiDatabaseHelper conexion = new MiDatabaseHelper(this);


    //nombre_funcion(int, string, long)

        boton_guardar.setOnClickListener(view ->{

            String nombre = texto.getText().toString();
            conexion.insertarUsuario(nombre,25);
        }
        );

    }
}