package com.bestteam.libvidd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMenuActivity extends AppCompatActivity {

    Button aboutBtn, mapBtn, detalesBtn, adminBtn , addMovieBtn, addActorBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        aboutBtn = findViewById(R.id.AboutBtnM2);
        mapBtn = findViewById(R.id.MapBtnM2);
        adminBtn = findViewById(R.id.adminMenuBtn);
        detalesBtn = findViewById(R.id.editUserBtnM2);
        addMovieBtn = findViewById(R.id.addMovieBtnM2);
        addActorBtn = findViewById(R.id.addActorBtnM2);

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenuActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        detalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenuActivity.this,UserDashboardActivity.class);
                startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenuActivity.this,MapsActivity.class);
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

        adminBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminMenuActivity.this,AdminDashboardActivity.class);
                    startActivity(intent);
                }
            });
    }
}
