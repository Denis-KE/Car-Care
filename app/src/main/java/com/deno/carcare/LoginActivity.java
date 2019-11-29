package com.deno.carcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button mBtnlogin;
    EditText edtname,edtPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnlogin=findViewById(R.id.btnLogin);
        edtname =findViewById(R.id.edtloginMail);
        edtPassword =findViewById(R.id.edtloginPass);
        mAuth = FirebaseAuth.getInstance();

        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (name.isEmpty()){
                    edtname.setError("Please Enter user Name");
                    return;
                }
                if (password.isEmpty()){
                    edtPassword.setError("Please Enter Password");
                    return;
                }
                mAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
