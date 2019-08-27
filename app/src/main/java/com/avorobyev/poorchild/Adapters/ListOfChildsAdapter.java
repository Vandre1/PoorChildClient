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

import com.avorobyev.poorchild.Dao.Children;
import com.avorobyev.poorchild.Dao.Task;
import com.avorobyev.poorchild.Parent.ListOfTasksActivity;
import com.avorobyev.poorchild.Parent.ViewTaskActivity;
import com.avorobyev.poorchild.R;

import java.util.ArrayList;

public class ListOfChildsAdapter extends RecyclerView.Adapter<ListOfChildsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout viewContainer;

        public ViewHolder(ConstraintLayout viewContainer) {
            super(viewContainer);
            this.viewContainer = viewContainer;
        }
    }

    private ArrayList<Children> listOfChildsDataSet;
    private Context context;

    public ListOfChildsAdapter(ArrayList<Children> listOfChildsDataSet, Context context) {
        this.listOfChildsDataSet = listOfChildsDataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout viewContainer = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_item_list_of_childs, parent, false);
        ViewHolder vh = new ViewHolder(viewContainer);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get work for data bind
        final Children childrenForBind = listOfChildsDataSet.get(position);

        // Get views for databind
        TextView activeTasksStringTextView = holder.viewContainer.findViewById(R.id.activeTasksStringTextView);
        TextView allTasksStringTextView = holder.viewContainer.findViewById(R.id.allTasksStringTextView);
        TextView activeTasksCountTextView = holder.viewContainer.findViewById(R.id.activeTasksCountTextView);
        TextView allTasksCountTextView = holder.viewContainer.findViewById(R.id.allTasksCountTextView);
        TextView childNameTextView = holder.viewContainer.findViewById(R.id.childNameTextView);
        Button selectChildButton = holder.viewContainer.findViewById(R.id.selectChildButton);

        selectChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputIntent = new Intent(context, ListOfTasksActivity.class);
                inputIntent.putExtra(ListOfTasksActivity.CHILDREN_ID, childrenForBind.Id);
                context.startActivity(inputIntent);
            }
        });

        // Databind self
        childNameTextView.setText(childrenForBind.FirstName);

        int activeTasksCount = 0;
        int allTasksCount = 0;

        for (Task task : childrenForBind.Tasks) {
            if (task.DateTimeConfirmed != null) {
                continue;
            }

            allTasksCount++;

            if (task.CalculateIsTaskActiveNow()) {
                activeTasksCount++;
            }
        }

        activeTasksCountTextView.setText(String.valueOf(activeTasksCount));
        allTasksCountTextView.setText(String.valueOf(allTasksCount));
    }

    @Override
    public int getItemCount() {
        return listOfChildsDataSet.size();
    }
}
