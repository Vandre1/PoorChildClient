package com.avorobyev.poorchild.Adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avorobyev.poorchild.Dao.Children;

        public ConstraintLayout viewContainer;

        public ViewHolder(ConstraintLayout viewContainer) {
            super(viewContainer);
            this.viewContainer = viewContainer;
        }
    }

    private ArrayList<Children> listOfChildsDataSet;

    public ListOfChildsAdapter(ArrayList<Children> listOfChildsDataSet) {
        this.listOfChildsDataSet = listOfChildsDataSet;
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
        Children childrenForBind = listOfChildsDataSet.get(position);

        // Get views for databind
        TextView childNameTextView = holder.viewContainer.findViewById(R.id.childNameTextView);
        TextView activeTasksTextView = holder.viewContainer.findViewById(R.id.activeTasksTextView);
        Button selectChildButton = holder.viewContainer.findViewById(R.id.selectChildButton);

        // Databind self
        childNameTextView.setText(childrenForBind.FirstName);

        // TODO: реализовать подсчет кол-ва активных задач
        activeTasksTextView.setText("5");
    }

    @Override
    public int getItemCount() {
        return listOfChildsDataSet.size();
    }
}
