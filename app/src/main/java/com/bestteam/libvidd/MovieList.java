package com.bestteam.libvidd;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arkadi on 26/12/2017.
 */

public class MovieList extends ArrayAdapter<Movie>
{
    private Activity context;
    private List<Movie> movies;

    public MovieList(Activity context, List<Movie> movieList)
    {
        super(context, R.layout.movielist_layout , movieList);
        this.context = context;
        this.movies = movieList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.movielist_layout,null,true);

        TextView textViewMovieName = listViewItem.findViewById(R.id.textViewMovieName);
        TextView textViewRating = listViewItem.findViewById(R.id.textViewRating);

        Movie movie = movies.get(position);

        textViewMovieName.setText(movie.getMovieName());
        textViewRating.setText(String.valueOf(movie.getMovieRating()));
        return listViewItem;
    }
}
