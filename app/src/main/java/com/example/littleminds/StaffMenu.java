package com.example.littleminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StaffMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_menu);

        //Buttons
        Button btn1 = findViewById(R.id.option1);
        Button btn2 = findViewById(R.id.option2);
        Button btn3 = findViewById(R.id.option3);
        Button btn4 = findViewById(R.id.option4);

        //Go to Register Staff page
        btn1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), RegisterStaff.class);
                startActivity(newActivity);
            }
        });
        //Go to Register Teacher page
        btn2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), RegisterTeacher.class);
                startActivity(newActivity);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), AddStudent.class);
                startActivity(newActivity);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), ViewStudent.class);
                startActivity(newActivity);
            }
        });

    }
}