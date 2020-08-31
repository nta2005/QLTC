package com.nta.qltc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.nta.qltc.R;

public class IntroActivity extends AppCompatActivity {
    ImageView img;
    Animation animationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        img = findViewById(R.id.img);
        animationImage = AnimationUtils.loadAnimation(this, R.anim.anim_hello);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroActivity.this, IntroActivity2.class));
                finish();
            }
        }, 1111);
    }
}
