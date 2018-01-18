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
 * Created by Arkadi on 16/01/2018.
 */

public class ActorList extends ArrayAdapter<Actor>
{
    private Activity context;
    private List<Actor> actors;

    public ActorList(Activity context, List<Actor> ActorList)
    {
        super(context, R.layout.actorlist_layout , ActorList);
        this.context = context;
        this.actors = ActorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.actorlist_layout,null,true);

        TextView textViewActorName = listViewItem.findViewById(R.id.textViewActorName2);
        TextView textViewRating = listViewItem.findViewById(R.id.textViewRating3);

        Actor Actor = actors.get(position);

        textViewActorName.setText(Actor.getActorName());
        textViewRating.setText(String.valueOf(Actor.getActorRating()));
        return listViewItem;
    }
}
