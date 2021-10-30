package com.example.littleminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import android.os.Bundle;

public class StudentStatus extends AppCompatActivity {

    private Button add_btn;
    private EditText S_num, Status1, Status2, Status3, Status4;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_status);
        add_btn= (Button) findViewById(R.id.btn);
        S_num= (EditText) findViewById(R.id.num);
        Status1= (EditText) findViewById(R.id.status1);
        Status2= (EditText) findViewById(R.id.status2);
        Status3= (EditText) findViewById(R.id.status3);
        Status4= (EditText) findViewById(R.id.status4);

        loadingBar = new ProgressDialog(this);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }
    private void add()
    {
        String st_num = S_num.getText().toString();
        String st_Status1 = Status1.getText().toString();
        String st_Status2 = Status2.getText().toString();
        String st_Status3 = Status3.getText().toString();
        String st_Status4 = Status4.getText().toString();



        if (TextUtils.isEmpty(st_num))
        {
            Toast.makeText(this, "Please write student number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(st_Status1))
        {
            Toast.makeText(this, "Please write student language skill performance", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(st_Status2))
        {
            Toast.makeText(this, "Please write student Motor skill performance", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(st_Status3))
        {
            Toast.makeText(this, "Please write student Reasoning skill performance", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(st_Status4))
        {
            Toast.makeText(this, "Please write student Social skill performance", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Add Status");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateNum(st_num, st_Status1, st_Status2, st_Status3, st_Status4);


        }

    }

    private void ValidateNum(final String num, final String status1, final String status2, final String status3, final String status4)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Students_Status").child(num).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("num", num);
                    userdataMap.put("status1", status1);
                    userdataMap.put("status2", status2);
                    userdataMap.put("status3", status3);
                    userdataMap.put("status4", status4);


                    RootRef.child("Students_Status").child(num).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(StudentStatus.this, "Student status added.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(StudentStatus.this, TeacherMenu.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(StudentStatus.this, "Network Error: Please try again after some time", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(StudentStatus.this, "This " + num + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(StudentStatus.this, "This student id status already inserted. Please Go to update page if you want to update", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(StudentStatus.this, TeacherMenu.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}