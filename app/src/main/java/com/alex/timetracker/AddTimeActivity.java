package com.alex.timetracker;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class AddTimeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_time);
    }

    public void onSave(View v){
        EditText time = (EditText) findViewById(R.id.time_edit_text);
        EditText note = (EditText) findViewById(R.id.notes_edit_text);
        Intent intent = getIntent();
        intent.putExtra("time", time.getText().toString());
        intent.putExtra("note", note.getText().toString());
        this.setResult(RESULT_OK, intent);
        finish();
    }

    public void onCancel(View v){
        finish();
    }
}
