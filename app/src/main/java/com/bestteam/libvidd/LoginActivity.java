package com.bestteam.libvidd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity
{
    public static String USER_NAME = "userName";
    public static String USER_ID = "userId";
    public static boolean ADMIN = false;

    EditText editTextName;
    EditText editTextpassword;
    Button btnSignIn;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextName = findViewById(R.id.editTextUserNameLogin);
        editTextpassword = findViewById(R.id.editTextPasswordLogin);
        btnSignIn = findViewById(R.id.LoginBtnLogin);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = editTextName.getText().toString().trim();
                final String password = editTextpassword.getText().toString().trim();

                databaseUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot userSnapShot : dataSnapshot.getChildren())
                        {
                            User user = userSnapShot.getValue(User.class);
                            Log.e("Get Data", user.getUserName());
                            if(user.getPassword().equals(password) && user.getUserName().equals(userName) && user.isAdmin())
                            {
                                Toast.makeText(LoginActivity.this,"You are In!", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(LoginActivity.this,AdminMenuActivity.class);
                                USER_NAME = userName; USER_ID = user.getUserId(); ADMIN =  user.isAdmin();
                                startActivity(intent);
                                finish();
                            }
                            else if(user.getPassword().equals(password) && user.getUserName().equals(userName) && user.isAdmin() == false)
                            {
                                Toast.makeText(LoginActivity.this,"You are In!", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                                USER_NAME = userName; USER_ID = user.getUserId(); ADMIN =  user.isAdmin();
                                startActivity(intent);
                                finish();
                            }
                        }
                        Toast.makeText(LoginActivity.this,"wrong user/pass pleas try again or SignUp", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                    }
                });

            }
        });
    }
}
