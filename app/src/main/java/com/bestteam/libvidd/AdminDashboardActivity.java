package com.bestteam.libvidd;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity
{
    EditText editTextName;
    EditText editTextPassword;
    Button btnAdd;
    Spinner spinnerG;

    DatabaseReference databaseUsers;

    ListView listViewUsers;

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        editTextName = findViewById(R.id.editTextNameADM);
        btnAdd = findViewById(R.id.AddUserBtnADM);
        spinnerG = findViewById(R.id.spinnerGADM);
        editTextPassword = findViewById(R.id.editTextPasswordADM);

        listViewUsers = findViewById(R.id.listViewUsersADM);

        userList = new ArrayList<>();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addUser()
                //the method defined below
                //this method is actually performing the write operation
                addUser();
            }
        });

        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                User user = userList.get(i);

                showUpdateDialog(user.getUserName(), user.getUserId());

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapShot : dataSnapshot.getChildren())
                {
                    User user = userSnapShot.getValue(User.class);
                    userList.add(user);
                }

                // update the list
                UsersList adapter = new UsersList(AdminDashboardActivity.this, userList);
                listViewUsers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(String userName, final String userId)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null );

        dialogBuilder.setView(dialogView);

        final Spinner spinnerGenres = dialogView.findViewById(R.id.spinnerGanres);
        final EditText editTextName = dialogView.findViewById(R.id.editTextNamee);
        final EditText editTextPassword = dialogView.findViewById(R.id.editTextPasswordUp);
        final Button buttonUpdate = dialogView.findViewById(R.id.UpdateBtn);
        final Button buttonDelete = dialogView.findViewById(R.id.DeleteBtn);

        dialogBuilder.setTitle("Updating User: " + userName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
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
                updateUser(userId , name ,password, genre);

                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(userId);
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

    private void addUser()
    {
        //getting values to save
        String name = editTextName.getText().toString().trim();
        String genre = spinnerG.getSelectedItem().toString();
        String password = editTextPassword.getText().toString().trim();

        //checking if the value is provided
        if(!TextUtils.isEmpty(name))
        {
            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our User
            String id = databaseUsers.push().getKey();
            User user = new User(id , name ,password, genre);

            //Saving the User
            databaseUsers.child(id).setValue(user);

            //setting editText to blank again
            editTextName.setText("");
            editTextPassword.setText("");

            //displaying a success toast
            Toast.makeText(this, "user added", Toast.LENGTH_LONG).show();
        }else
        {
            //if the value is not given displaying a toast
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
