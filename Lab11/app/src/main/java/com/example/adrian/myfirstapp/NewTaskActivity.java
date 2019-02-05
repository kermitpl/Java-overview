package com.example.adrian.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            String name = extras.getString("previousName");
            String description = extras.getString("previousDescription");
            String date = extras.getString("previousDate");
            position = extras.getInt("position");

            EditText editTextName = (EditText) findViewById(R.id.editTextName);
            editTextName.setText(name);

            EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
            editTextDescription.setText(description);

            EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
            DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
            DateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date newDate =  df.parse(date);
                String dateToShow = df2.format(newDate);
                editTextDate.setText(dateToShow);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {position=-1;}
    }

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = new Intent();
        setResult(-1, intent);
    }

    public void onSaveButtonClick (View view) {
        Intent intent = new Intent();

        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        String value1 = editTextName.getText().toString();

        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        String value2 = editTextDescription.getText().toString();

        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        String value3 = editTextDate.getText().toString();

        intent.putExtra("NameOfTask", value1); //value should be your string from the edittext
        intent.putExtra("DescriptionOfTask", value2);
        intent.putExtra("DateOfTask", value3);
        intent.putExtra("Position", position);
        setResult(1, intent); //The data you want to send back
        finish();
    }
}
