package com.example.hassanihsan.project1dogwalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTrackingactivity extends AppCompatActivity {


    //variables
    EditText editText_name, editText_date;
    Button button_add,button_view, button_settings;

    //oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trackingactivity);

        //setting buttons/edittext to there ids
        editText_name = findViewById(R.id.edittext_name);
        editText_date = findViewById(R.id.editText_Date);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);
        button_settings = findViewById(R.id.button_settings);

        //add button on click code
        //to add dog
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringDate = editText_date.getText().toString();

                if (stringName.length() <=0 ||   stringDate.length() <= 0){
                    Toast.makeText(AddTrackingactivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(AddTrackingactivity.this);
                    DogModelClass dogModelClass = new DogModelClass(stringName,stringDate);
                    databaseHelperClass.addDogs(dogModelClass);

                    Toast.makeText(AddTrackingactivity.this, "Add Dog Successfully" , Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        //view button onclick method
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTrackingactivity.this,ViewDogActivity.class);
                startActivity(intent);
            }
        });

        //settings button onclick method
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTrackingactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
