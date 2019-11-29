package com.deno.carcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button btnreg,btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlog =findViewById(R.id.btnlog);
        btnreg =findViewById(R.id.btnreg);

    btnreg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));

        }
    });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });



    }
}
