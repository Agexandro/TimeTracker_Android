package com.alex.timetracker;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TImeTrackerAdapter extends CursorAdapter {
    public TImeTrackerAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.time_list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.time_view);
        TextView valueTextView = (TextView) view.findViewById(R.id.note_view);
        nameTextView.setText(cursor.getString(cursor.getColumnIndex("time")));
        valueTextView.setText(cursor.getString(cursor.getColumnIndex("note")));
    }
}
