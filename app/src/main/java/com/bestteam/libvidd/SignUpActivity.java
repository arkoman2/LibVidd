package com.bestteam.libvidd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity
{
    public static String id;

    EditText editTextName;
    EditText editTextpassword;
    Button btnSignUp, choosePic;
    Spinner spinnerG;

    DatabaseReference databaseUsers;

    public SignUpActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        editTextName = findViewById(R.id.editTextUserNameSIGN);
        editTextpassword = findViewById(R.id.editTextPasswordSIGN);
        btnSignUp = findViewById(R.id.AddUserBtnSIGN);
        spinnerG = findViewById(R.id.spinnerGSIGN);

        choosePic = findViewById(R.id.addPicBtn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addUser()
                //the method defined below
                //this method is actually performing the write operation
                addUser();
            }
        });
    }

    private void addUser()
    {
        //getting values to save
        String name = editTextName.getText().toString().trim();
        String genre = spinnerG.getSelectedItem().toString();
        String password = editTextpassword.getText().toString().trim();

        //checking if the value is provided
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password))
        {
            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our User
            id = databaseUsers.push().getKey();
            User user = new User(id , name , password, genre);

            //Saving the User
            databaseUsers.child(id).setValue(user);

//            //setting editText to blank again
//            editTextName.setText("");

            //displaying a success toast
            Toast.makeText(this, "user added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignUpActivity.this,AddPicAfterActivity.class);
            startActivity(intent);
        }else
        {
            //if the value is not given displaying a toast
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }


}
