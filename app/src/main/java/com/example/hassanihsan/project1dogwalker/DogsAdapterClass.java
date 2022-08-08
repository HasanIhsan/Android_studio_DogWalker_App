package com.example.hassanihsan.project1dogwalker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogsAdapterClass extends RecyclerView.Adapter<DogsAdapterClass.ViewHolder> {

    //variables
    List<DogModelClass> dog;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    //adapter method
    public DogsAdapterClass(List<DogModelClass> dogs, Context context) {
        this.dog = dogs;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.dog_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final DogModelClass employeeModelClass = dog.get(position);


        holder.editText_Name.setText(employeeModelClass.getName());
        holder.editText_Date.setText(employeeModelClass.getDate());



        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteDog(employeeModelClass.getId());
                dog.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    //getitem count
    @Override
    public int getItemCount() {
        return dog.size();
    }

    //holder method
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView editText_Name;
        TextView editText_Date;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Date = itemView.findViewById(R.id.editText_Date);
            button_delete = itemView.findViewById(R.id.button_delete);


        }
    }
}
