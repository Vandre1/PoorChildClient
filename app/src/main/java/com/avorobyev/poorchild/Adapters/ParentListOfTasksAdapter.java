package com.avorobyev.poorchild.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avorobyev.poorchild.Dao.TaskSchedule;
import com.avorobyev.poorchild.R;

import java.util.ArrayList;

public class ParentListOfTasksAdapter extends RecyclerView.Adapter<ParentListOfTasksAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout viewContainer;

        public ViewHolder(ConstraintLayout viewContainer) {
            super(viewContainer);
            this.viewContainer = viewContainer;
        }
    }

    private ArrayList<TaskSchedule> listOfTasksDataSet;
    private Context context;

    public ParentListOfTasksAdapter(ArrayList<TaskSchedule> listOfTasksDataSet, Context context) {
        this.listOfTasksDataSet = listOfTasksDataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout viewContainer = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_item_list_of_tasks, parent, false);
        ViewHolder vh = new ViewHolder(viewContainer);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get work for data bind
        TaskSchedule taskScheduleForBind = listOfTasksDataSet.get(position);

        // Get views for databind
        TextView taskScheduleNameTextView = holder.viewContainer.findViewById(R.id.taskScheduleNameTextView);
        TextView taskScheduleDescriptionTextView = holder.viewContainer.findViewById(R.id.taskScheduleDescriptionTextView);
        Button viewTaskScheduleButton = holder.viewContainer.findViewById(R.id.viewTaskScheduleButton);

        // Databind self
        taskScheduleNameTextView.setText(taskScheduleForBind.Name);
        taskScheduleDescriptionTextView.setText(taskScheduleForBind.Description);
    }

    @Override
    public int getItemCount() {
        return listOfTasksDataSet.size();
    }
}
