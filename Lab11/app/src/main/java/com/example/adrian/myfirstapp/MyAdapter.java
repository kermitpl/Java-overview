package com.example.adrian.myfirstapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    ListActivity mActivity;

    private List<MyTask> mTasks = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mDescription;
        public TextView mDeadline;

        public MyViewHolder(View pItem) {
            super(pItem);
            mName = (TextView) pItem.findViewById(R.id.task_name);
            mDescription = (TextView) pItem.findViewById(R.id.task_description);
            mDeadline = (TextView) pItem.findViewById(R.id.task_deadline);
        }
    }

    public MyAdapter(ListActivity activity, List<MyTask> pTasks, RecyclerView pRecyclerView){
        mActivity = activity;
        mTasks = pTasks;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_layout, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionToEdit= mRecyclerView.getChildAdapterPosition(v);
                Intent intent = new Intent(mActivity, NewTaskActivity.class);
                intent.putExtra("previousName", mTasks.get(positionToEdit).getName());
                intent.putExtra("previousDescription", mTasks.get(positionToEdit).getDescription());
                intent.putExtra("previousDate", mTasks.get(positionToEdit).getDeadline().toString());
                intent.putExtra("position", positionToEdit);
                mActivity.startActivityForResult(intent, 1);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                mTasks.remove(positionToDelete);
                notifyItemRemoved(positionToDelete);
                mActivity.save();
                return false;
            }
        });

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        MyTask task = mTasks.get(i);
        ((MyViewHolder) viewHolder).mName.setText(task.getName());
        ((MyViewHolder) viewHolder).mDescription.setText(task.getDescription());
        DateFormat df2 = new SimpleDateFormat("EEE dd.MM.yyyy");
        String dateToShow = df2.format(task.getDeadline());
        ((MyViewHolder) viewHolder).mDeadline.setText(String.valueOf(dateToShow));
        if (task.getDeadline().before(Calendar.getInstance().getTime()))
        ((MyViewHolder) viewHolder).mDeadline.setTextColor(Color.RED);
        else ((MyViewHolder) viewHolder).mDeadline.setTextColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }
}