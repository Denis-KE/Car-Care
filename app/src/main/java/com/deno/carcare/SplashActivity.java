package com.deno.carcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splash = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();


                }
            }
        };
        splash.start();

    }



}
