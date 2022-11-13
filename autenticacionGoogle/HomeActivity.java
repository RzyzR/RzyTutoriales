package com.rzy.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    //Variables
    //Componentes
    Button mButtonCerrar;

    //Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonCerrar = findViewById(R.id.btnCerrar);

        mAuth = FirebaseAuth.getInstance();

        mButtonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();//Cerramos la sesión
                irMain();
            }
        });
    }

    //Verificamos el inicio de sesion

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario = mAuth.getCurrentUser();
        if(usuario == null){
            irMain();
        }
    }

    private void irMain() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        //con este metodo iniciamos y con
        finish();
        //evitamos el regreso a esta actividad
    }

    //FIN :){ A COMPILAR }
}
