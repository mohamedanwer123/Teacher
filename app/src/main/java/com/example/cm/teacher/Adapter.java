package com.example.cm.teacher;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cm on 12/08/2017.
 */

public class Adapter extends ArrayAdapter {
    Context context;
    int res;
    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<data> objects) {
        super(context, resource, objects);
        this.context = context;
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(res,parent,false);

        TextView id = convertView.findViewById(R.id.student_id);
        TextView name = convertView.findViewById(R.id.student_name);

        data data = (data) getItem(position);

        id.setText(String.valueOf(data.getId()));
        name.setText(data.getName());

        return convertView;
    }
}
