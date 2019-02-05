package com.example.adrian.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<MyTask> myTaskData;
    private String keyForSP = "mojaapka123naserio";

    public void save() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(myTaskData);

        editor.putString(keyForSP, json);
        editor.commit();
    }

    private ArrayList<MyTask> restore() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(keyForSP, null);
        Type type = new TypeToken<ArrayList<MyTask>>() {}.getType();
        ArrayList<MyTask> arrayList = null;
        arrayList = gson.fromJson(json, type);
        return arrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myTaskData = new ArrayList<>();
        if (restore() != null) {
            myTaskData.clear();
            myTaskData.addAll(restore());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.tasks_list);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(this, myTaskData, mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

    }

    public void goToAnActivity(View view) {
        Intent Intent = new Intent(this, NewTaskActivity.class);
        startActivityForResult(Intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != -1) {
            if (requestCode == 0) {
                String newName = data.getStringExtra("NameOfTask");
                String newDescription = data.getStringExtra("DescriptionOfTask");
                String newDate = data.getStringExtra("DateOfTask");
                addTask(newName, newDescription, newDate);
            } else if (requestCode == 1) {
                String newName = data.getStringExtra("NameOfTask");
                String newDescription = data.getStringExtra("DescriptionOfTask");
                String newDate = data.getStringExtra("DateOfTask");
                int position = data.getIntExtra("Position", 0);
                editTask(newName, newDescription, newDate, position);
            }
        }
    }

    public void addTask(String name, String description, String date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date newDate =  df.parse(date);
            MyTask newtask = new MyTask(name, description, newDate);
            myTaskData.add(newtask);
            int position = myTaskData.indexOf(newtask);
            mAdapter.notifyItemInserted(position);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong date format.", Toast.LENGTH_LONG).show();
        }
        save();
    }

    public void editTask(String name, String description, String date, int position) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date newDate =  df.parse(date);

            myTaskData.get(position).setName(name);
            myTaskData.get(position).setDescription(description);
            myTaskData.get(position).setDeadline(newDate);

            mAdapter.notifyItemChanged(position);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong date format.", Toast.LENGTH_LONG).show();
        }
        save();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (restore() != null) {
            myTaskData.clear();
            myTaskData.addAll(restore());
        }
    }
}
