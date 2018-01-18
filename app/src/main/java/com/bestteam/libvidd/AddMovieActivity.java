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

public class AddMovieActivity extends AppCompatActivity {

    TextView textViewUserName;
    EditText editTextMovieName;
    SeekBar seekBarRating;

    Button btnAddMovie;

    ListView listViewMovies;

    DatabaseReference databaseMovies;

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        textViewUserName = findViewById(R.id.textViewUserName1);
        editTextMovieName = findViewById(R.id.editTextMovieName);
        seekBarRating = findViewById(R.id.seekBarRating);


        btnAddMovie = findViewById(R.id.AddMovieBtn);
        listViewMovies = findViewById(R.id.listViewMovies);

        Intent intent = getIntent();

        movies = new ArrayList<>();

        String id = intent.getStringExtra("userId");

        String name = intent.getStringExtra("userName");

        textViewUserName.setText(name);

        databaseMovies = FirebaseDatabase.getInstance().getReference("Movies").child(id);

        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMovie();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseMovies.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                movies.clear();

                for (DataSnapshot movieSnapshot : dataSnapshot.getChildren())
                {
                    Movie movie = movieSnapshot.getValue(Movie.class);
                    movies.add(movie);
                }

                MovieList movieListAdapter = new MovieList(AddMovieActivity.this , movies);
                listViewMovies.setAdapter(movieListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveMovie()
    {
        String movieName = editTextMovieName.getText().toString().trim();
        int rating = seekBarRating.getProgress();

        if(!TextUtils.isEmpty(movieName))
        {
            String id = databaseMovies.push().getKey();

            Movie movie = new Movie(id , movieName , rating);

            databaseMovies.child(id).setValue(movie);

            Toast.makeText(this , "Movie Saved succesfully",Toast.LENGTH_LONG).show();

        }else
        {
            Toast.makeText(this, "Movie Name should not be empty", Toast.LENGTH_LONG).show();
        }
    }
}
