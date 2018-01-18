package com.bestteam.libvidd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity
{
    Button aboutBtn, mapBtn, detalesBtn, addMovieBtn, addActorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        aboutBtn = findViewById(R.id.AboutBtnM);
        mapBtn = findViewById(R.id.MapBtnM);
        detalesBtn = findViewById(R.id.editUserBtnM);
        addMovieBtn = findViewById(R.id.addMovieBtnM);
        addActorBtn = findViewById(R.id.addActorBtnM);

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        detalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,UserDashboardActivity.class);
                startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        addMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);

                intent.putExtra("userId" , LoginActivity.USER_ID);
                intent.putExtra("userName" , LoginActivity.USER_NAME);

                startActivity(intent);
            }
        });

        addActorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), AddFavActActivity.class);

                intent.putExtra("userId" , LoginActivity.USER_ID);
                intent.putExtra("userName" , LoginActivity.USER_NAME);

                startActivity(intent);
            }
        });
    }
}
