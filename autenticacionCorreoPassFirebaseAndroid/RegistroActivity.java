package com.rzy.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    EditText mEditTextEmail;
    EditText mEditTextPass;
    EditText mEditTextConfirmarPass;
    Button mButtonRegistrar;
    TextView mTextViewRespuestaR;

    FirebaseAuth mAuth;

    String email;
    String pass;
    String conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextPass = findViewById(R.id.editTextPass);
        mEditTextConfirmarPass = findViewById(R.id.editConfirmarPass);
        mButtonRegistrar = findViewById(R.id.btnRegistrar);
        mTextViewRespuestaR = findViewById(R.id.textViewRespuestaR);

        mAuth = FirebaseAuth.getInstance();

        mButtonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString().trim();
                pass = mEditTextPass.getText().toString().trim();
                conPass = mEditTextConfirmarPass.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty() || conPass.isEmpty()) {
                    mTextViewRespuestaR.setText("Ingrese datos!!");
                    mTextViewRespuestaR.setTextColor(Color.RED);
                } else if (!email.isEmpty() && !pass.isEmpty() && !conPass.isEmpty()) {
                    if (emailValido(email)) {
                        if (pass.equals(conPass)) {
                            if (conPass.length() < 6) {
                                mTextViewRespuestaR.setText("La contraseña debe tener mas de 6 caracteres");
                                mTextViewRespuestaR.setTextColor(Color.RED);
                            } else {
                                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            mTextViewRespuestaR.setText("CUENTA CREADA CON EXITO");
                                            mTextViewRespuestaR.setTextColor(Color.GREEN);
                                        } else {
                                            mTextViewRespuestaR.setText("LO MAS PROBABLE ES QUE LA CUENTA YA EXISTE");
                                            mTextViewRespuestaR.setTextColor(Color.BLUE);
                                        }
                                    }
                                });
                            }
                        } else {
                            mTextViewRespuestaR.setText("Los campos no coinciden");
                            mTextViewRespuestaR.setTextColor(Color.RED);
                        }
                    }else{
                        mTextViewRespuestaR.setText("Email invalido");
                        mTextViewRespuestaR.setTextColor(Color.RED);
                    }

                }
            }
        });
    }

    private boolean emailValido(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
