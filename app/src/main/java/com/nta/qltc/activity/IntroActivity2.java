package com.nta.qltc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nta.qltc.R;

public class IntroActivity2 extends AppCompatActivity {

    TextView txtTile;
    ImageView img_intro;
    Animation animationText, animationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);
        txtTile = findViewById(R.id.txt_intro);
        img_intro = findViewById(R.id.img_intro);
        animationText = AnimationUtils.loadAnimation(this, R.anim.text_intro_animation);
        txtTile.setAnimation(animationText);
        animationImage = AnimationUtils.loadAnimation(this, R.anim.image_intro_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroActivity2.this, LoginActivity.class));
                finish();
            }
        }, 1500);
    }
}