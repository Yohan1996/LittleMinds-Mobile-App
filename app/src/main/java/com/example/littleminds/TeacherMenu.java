package com.example.littleminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);

        Button btn1 = findViewById(R.id.option1);
        Button btn2 = findViewById(R.id.option2);
        Button btn3 = findViewById(R.id.option3);


        btn1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), ViewStudent.class);
                startActivity(newActivity);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), StudentStatus.class);
                startActivity(newActivity);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), ViewStatus.class);
                startActivity(newActivity);
            }
        });




    }
}