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

public class ChildListOfWorksAdapter extends RecyclerView.Adapter<ChildListOfWorksAdapter.ViewHolder> {

    private ArrayList<Work> listOfWorksDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout viewContainer;

        public ViewHolder(ConstraintLayout viewContainer) {
            super(viewContainer);
            this.viewContainer = viewContainer;
        }
    }

    public ChildListOfWorksAdapter(ArrayList<Work> listOfWorksDataSet) {
        this.listOfWorksDataSet = listOfWorksDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout viewContainer = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_item_list_of_works, parent, false);
        ViewHolder vh = new ViewHolder(viewContainer);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get work for data bind
        Work workForBind = listOfWorksDataSet.get(position);

        // Get views for databind
        TextView workNameTextView = holder.viewContainer.findViewById(R.id.workNameTextView);
        TextView workDescriptionTextView = holder.viewContainer.findViewById(R.id.workDescriptionTextView);
        Button workCompletedButton = holder.viewContainer.findViewById(R.id.workCompletedButton);

        // Databind self
        workNameTextView.setText(workForBind.Name);
        workDescriptionTextView.setText(workForBind.Description);
        workCompletedButton.setText("Готово");
    }

    @Override
    public int getItemCount() {
        return listOfWorksDataSet.size();
    }
}
