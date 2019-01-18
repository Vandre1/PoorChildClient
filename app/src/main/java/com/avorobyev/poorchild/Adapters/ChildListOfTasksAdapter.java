package com.avorobyev.poorchild.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.R;

import java.util.ArrayList;

public class ChildListOfTasksAdapter extends RecyclerView.Adapter<ChildListOfTasksAdapter.ViewHolder> {

    private ArrayList<Task> listOfTasksDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout viewContainer;

        public ViewHolder(ConstraintLayout viewContainer) {
            super(viewContainer);
            this.viewContainer = viewContainer;
        }
    }

    public ChildListOfTasksAdapter(ArrayList<Task> listOfTasksDataSet) {
        this.listOfTasksDataSet = listOfTasksDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout viewContainer = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_item_list_of_tasks, parent, false);
        ViewHolder vh = new ViewHolder(viewContainer);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get work for data bind
        Task taskForBind = listOfTasksDataSet.get(position);

        // Get views for databind
        TextView taskNameTextView = holder.viewContainer.findViewById(R.id.workNameTextView);
        TextView taskDescriptionTextView = holder.viewContainer.findViewById(R.id.workDescriptionTextView);
        Button taskCompletedButton = holder.viewContainer.findViewById(R.id.workCompletedButton);

        // Databind self
        taskNameTextView.setText(taskForBind.Name);
        taskDescriptionTextView.setText(taskForBind.Description);
    }

    @Override
    public int getItemCount() {
        return listOfTasksDataSet.size();
    }
}
