package com.avorobyev.poorchild.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.Parent.ViewTaskActivity;
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

    private ArrayList<Task> listOfTasksDataSet;
    private Context context;

    public ParentListOfTasksAdapter(ArrayList<Task> listOfTasksDataSet, Context context) {
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
        final Task taskForBind = listOfTasksDataSet.get(position);

        // Get views for databind
        TextView taskScheduleNameTextView = holder.viewContainer.findViewById(R.id.taskScheduleNameTextView);
        TextView taskScheduleDescriptionTextView = holder.viewContainer.findViewById(R.id.taskScheduleDescriptionTextView);
        TextView taskScheduleStatusTextView = holder.viewContainer.findViewById(R.id.taskScheduleStatusTextView);
        Button viewTaskScheduleButton = holder.viewContainer.findViewById(R.id.viewTaskScheduleButton);

        // Databind self
        taskScheduleNameTextView.setText(taskForBind.Name);
        taskScheduleDescriptionTextView.setText(taskForBind.Description);
        taskScheduleStatusTextView.setText(taskForBind.CalculateTaskStatusString());

        viewTaskScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputIntent = new Intent(context, ViewTaskActivity.class);
                String taskId = taskForBind.Id;
                inputIntent.putExtra(ViewTaskActivity.VIEW_TASK_ID, taskId);
                context.startActivity(inputIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfTasksDataSet.size();
    }
}
