package com.bestteam.libvidd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDashboardActivity extends AppCompatActivity
{
    EditText editTextName , editTextPassword;
    Button updateBtn, deleteBtn;
    Spinner spinnerGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        editTextName = findViewById(R.id.editTextNameUserEDIT);
        editTextPassword = findViewById(R.id.editTextPasswordEDIT);
        updateBtn = findViewById(R.id.UpdateBtnUserEDIT);
        deleteBtn = findViewById(R.id.DeleteBtnUserEDIT);
        spinnerGenres = findViewById(R.id.spinnerGanresUserEDIT);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenres.getSelectedItem().toString();
                String password = editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    editTextName.setText("Name required");
                    return;
                }
                updateUser(LoginActivity.USER_ID , name ,password, genre);
                Toast.makeText(UserDashboardActivity.this, "user is updated" , Toast.LENGTH_LONG).show();
                LoginActivity.USER_NAME = name;
                Intent intent = new Intent(UserDashboardActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(LoginActivity.USER_ID);
                Toast.makeText(UserDashboardActivity.this, "user is deleted" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserDashboardActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteUser(String userId)
    {
        DatabaseReference dataUser = FirebaseDatabase.getInstance().getReference("Users").child(userId);
        DatabaseReference dataMovies = FirebaseDatabase.getInstance().getReference("Movies").child(userId);

        dataUser.removeValue();
        dataMovies.removeValue();

        Toast.makeText(this, "user deleted succsefully" , Toast.LENGTH_LONG).show();
    }

    private boolean updateUser(String id , String name,String password, String genre)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(id);

        User user = new User(id , name , password,genre);

        databaseReference.setValue(user);

        Toast.makeText(this , "User updated succsesfully", Toast.LENGTH_LONG).show();

        return true;
    }
}
