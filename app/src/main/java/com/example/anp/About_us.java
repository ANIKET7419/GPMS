package com.example.anp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class About_us extends AppCompatActivity {

    TextView aniket,p,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        aniket=findViewById(R.id.aniket_info);
        p=findViewById(R.id.p_info);
        n=findViewById(R.id.n_info);
        YoYo.with(Techniques.Flash).repeat(YoYo.INFINITE).delay(0).playOn(aniket);
        YoYo.with(Techniques.Flash).repeat(YoYo.INFINITE).delay(0).playOn(n);
        YoYo.with(Techniques.Flash).repeat(YoYo.INFINITE).delay(0).playOn(p);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random i= new Random();
                        int k=i.nextInt()%11;

                        if(k<5) {
                            aniket.setTextColor(Color.GREEN);
                            n.setTextColor(Color.GREEN);
                            p.setTextColor(Color.GREEN);
                        }
                        else {
                            aniket.setTextColor(Color.YELLOW);
                            n.setTextColor(Color.YELLOW);
                            p.setTextColor(Color.YELLOW);

                        }
                    }
                });

            }
        }, 0, 1000);
    }
}
