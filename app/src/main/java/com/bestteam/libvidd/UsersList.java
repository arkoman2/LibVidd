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
 * Created by Arkadi on 24/12/2017.
 */

public class UsersList extends ArrayAdapter<User>
{
    private Activity context;
    private List<User> userList;

    public UsersList(Activity context, List<User> userList)
    {
        super(context, R.layout.list_layout , userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = listViewItem.findViewById(R.id.textViewGenre);

        User user = userList.get(position);

        textViewName.setText(user.getUserName());
        textViewGenre.setText(user.getUserGe());

        return listViewItem;
    }
}
