package com.example.littleminds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button user1 = findViewById(R.id.opt_b1);
        Button user2 = findViewById(R.id.opt_b2);

        //Go to Teacher Login
        user1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), TeacherLogin.class);
                startActivity(newActivity);
            }
        });


        //Go to Staff Login
        user2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent newActivity = new Intent(v.getContext(), StaffLogin.class);
                startActivity(newActivity);
            }
        });




    }

}