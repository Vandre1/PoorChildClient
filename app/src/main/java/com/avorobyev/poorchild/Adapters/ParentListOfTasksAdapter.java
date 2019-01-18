package com.avorobyev.poorchild.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avorobyev.poorchild.Model.Work;
import com.avorobyev.poorchild.R;

import java.util.ArrayList;

public class ParentListOfTasksAdapter extends RecyclerView.Adapter<ParentListOfTasksAdapter.ViewHolder> {

    private ArrayList<Work> listOfTasksDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout viewContainer;

        public ViewHolder(ConstraintLayout viewContainer) {
            super(viewContainer);
            this.viewContainer = viewContainer;
        }
    }

    public ParentListOfTasksAdapter(ArrayList<Work> listOfTasksDataSet) {
        this.listOfTasksDataSet = listOfTasksDataSet;
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
        Work workForBind = listOfTasksDataSet.get(position);

        // Get views for databind
        TextView workNameTextView = holder.viewContainer.findViewById(R.id.workNameTextView);
        TextView workDescriptionTextView = holder.viewContainer.findViewById(R.id.workDescriptionTextView);
        Button viewWorkButton = holder.viewContainer.findViewById(R.id.viewWorkButton);

        // Databind self
        workNameTextView.setText(workForBind.Name);
        workDescriptionTextView.setText(workForBind.Description);
    }

    @Override
    public int getItemCount() {
        return listOfTasksDataSet.size();
    }
}