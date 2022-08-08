package com.example.hassanihsan.project1dogwalker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ViewDogActivity extends AppCompatActivity {

    //variables
    RecyclerView recyclerView;
    Button button_deleteAll, button_AddMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dog);

        //set id to button and recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        button_deleteAll = findViewById(R.id.button_deleteAll);
        button_AddMore = findViewById(R.id.button_AddMore);

        //instanciate helper class
        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<DogModelClass> dogModelClasses = databaseHelperClass.getDogsList();

        //checks if there are any entrying in the adapter class for recyler view
        if (dogModelClasses.size() > 0){
           DogsAdapterClass dogadapterclass = new DogsAdapterClass(dogModelClasses,ViewDogActivity.this);
            recyclerView.setAdapter(dogadapterclass);
        }else {
            Toast.makeText(this, "There is no Dogs in the database", Toast.LENGTH_SHORT).show();
        }



        //deleteall Onclick method
        button_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(ViewDogActivity.this);
                databaseHelperClass.delteAllDogs();

                //restarting the activity to show that all values have been deleted
                Intent intent = new Intent(ViewDogActivity.this, ViewDogActivity.class);
                startActivity(intent);

            }
        });

        //add more onClick method (goes to add actavity)
        button_AddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDogActivity.this, AddTrackingactivity.class);
                startActivity(intent);
            }
        });

    }
}
