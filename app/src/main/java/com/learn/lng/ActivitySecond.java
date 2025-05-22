package com.learn.lng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView TV2 = findViewById(R.id.TV2);
        TextView TV3 = findViewById(R.id.TV3);
        Button Btn2 = findViewById(R.id.Btn2);
        Intent H = getIntent();
        String UserName = H.getStringExtra("name");
        int RNG = LuckyNumber();
        TV3.setText("" + RNG);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareData(UserName, RNG);
            }
        });

    }

    public int LuckyNumber() {
        Random LN = new Random();
        int PL = 777;
        int RNG = LN.nextInt(PL);
        return RNG;
    }

    public void ShareData(String UN, int RNG) {
        Intent H = new Intent(Intent.ACTION_SEND);
        H.setType("text/plain");
        H.putExtra(Intent.EXTRA_SUBJECT, UN + "You Got Lucky Today");
        H.putExtra(Intent.EXTRA_TEXT, "Your Lucky Number is: " + RNG);
        startActivity(Intent.createChooser(H, "Choose platform to share"));
    }
}