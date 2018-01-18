package com.bestteam.libvidd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddFavActActivity extends AppCompatActivity
{
    TextView textViewUserName;
    EditText editTextActorName;
    SeekBar seekBarRating;

    Button btnAddActor;

    ListView listViewActors;

    DatabaseReference databaseActors;

    List<Actor> Actors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fav_act);

        textViewUserName = findViewById(R.id.textViewUserName2);
        editTextActorName = findViewById(R.id.editTextActorName);
        seekBarRating = findViewById(R.id.seekBarRating2);


        btnAddActor = findViewById(R.id.AddActorBtn);
        listViewActors = findViewById(R.id.listViewActors);

        Intent intent = getIntent();

        Actors = new ArrayList<>();

        String id = intent.getStringExtra("userId");

        String name = intent.getStringExtra("userName");

        textViewUserName.setText(name);

        databaseActors = FirebaseDatabase.getInstance().getReference("Favorite Actors").child(id);

        btnAddActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveActor();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseActors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Actors.clear();

                for (DataSnapshot Actorsnapshot : dataSnapshot.getChildren())
                {
                    Actor Actor = Actorsnapshot.getValue(Actor.class);
                    Actors.add(Actor);
                }

                ActorList ActorListAdapter = new ActorList(AddFavActActivity.this , Actors);
                listViewActors.setAdapter(ActorListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveActor()
    {
        String ActorName = editTextActorName.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if(!TextUtils.isEmpty(ActorName))
        {
            String id = databaseActors.push().getKey();

            Actor actor = new Actor(id , ActorName , rating);

            databaseActors.child(id).setValue(actor);

            Toast.makeText(this , "Actor Saved succesfully",Toast.LENGTH_LONG).show();

        }else
        {
            Toast.makeText(this, "Actor Name should not be empty", Toast.LENGTH_LONG).show();
        }
    }
}
