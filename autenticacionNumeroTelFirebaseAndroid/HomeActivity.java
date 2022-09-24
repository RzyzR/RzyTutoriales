package com.rzy.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    Button mButtonSalir;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButtonSalir = findViewById(R.id.btnSalir);
        mAuth = FirebaseAuth.getInstance();

        mButtonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
