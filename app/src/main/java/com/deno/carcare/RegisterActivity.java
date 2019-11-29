package com.deno.carcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button mBtnRegister;
    EditText edtMail,edtPass,edtPhone;
    TextView tvAccount;
    ImageView imageView;
    static int REQUESCODE =1;
    static int PregCode =1;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actvity);
        mBtnRegister =findViewById(R.id.btnregister);
        edtMail=findViewById(R.id.edtMail);
        edtPass =findViewById(R.id.edtPass);
        edtPhone =findViewById(R.id.edtPhone);
        tvAccount=findViewById(R.id.tvAccount);
        imageView =findViewById(R.id.imageView);


        mAuth = FirebaseAuth.getInstance();

        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>22){

                    checkAndRequestForPermission ();
                }else {
                    openGallery ();
                }
            }
        });



            }
        });
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edtMail.getText().toString().trim();
                String Password = edtPass.getText().toString().trim();
                String Phone =edtPhone.getText().toString().trim();

                if (Email.isEmpty()) {
                    edtMail.setError("Please Enter Email");
                    return;

                }
                if (Password.isEmpty()) {
                    edtPass.setError("Please Enter Password");
                    return;
                }
                if (Phone.isEmpty()) {
                    edtPhone.setError("Please Enter Phone");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
            });

    }

    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "Please Accept Required terms", Toast.LENGTH_SHORT).show();
            }else {
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PregCode);
            }
        }
        else{
            openGallery();
        }
    }

    private void openGallery() {

        Intent gallery =new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery,REQUESCODE);
    }
}
