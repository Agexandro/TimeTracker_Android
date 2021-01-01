package com.alex.timetracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView times_list_view = null;
    TImeTrackerAdapter tImeTrackerAdapter = null;
    public static final int TIME_ENTRY_REQUEST_CODE = 1;
    private TimeListDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new TimeListDatabaseHelper(this);
        tImeTrackerAdapter = new TImeTrackerAdapter(this, databaseHelper.getAllTimeRecords());

        times_list_view = (ListView) findViewById(R.id.times_list);
        times_list_view.setAdapter(tImeTrackerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.time_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_time_menu_item){
            Intent intent = new Intent(this, AddTimeActivity.class);
            startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TIME_ENTRY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String time = data.getStringExtra("time");
                String note = data.getStringExtra("note");

                databaseHelper.saveTimeRecord(time, note);
                tImeTrackerAdapter.changeCursor(databaseHelper.getAllTimeRecords());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}