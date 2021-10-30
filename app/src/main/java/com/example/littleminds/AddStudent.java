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

import android.os.Bundle;

public class AddStudent extends AppCompatActivity {

    private Button add_btn;
    private EditText s_num, s_name, p_name, p_tp, t_name, t_tp;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        add_btn= (Button) findViewById(R.id.btn);
        s_num= (EditText) findViewById(R.id.num);
        s_name= (EditText) findViewById(R.id.name);

        p_name= (EditText) findViewById(R.id.parent_name);
        p_tp= (EditText) findViewById(R.id.parent_tp);
        t_name= (EditText) findViewById(R.id.teacher_name);
        t_tp= (EditText) findViewById(R.id.teacher_tp);
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
        String st_num = s_num.getText().toString();
        String st_name = s_name.getText().toString();
        String pa_name = p_name.getText().toString();
        String pa_tp = p_tp.getText().toString();
        String te_name = t_name.getText().toString();
        String te_tp = t_tp.getText().toString();


        if (TextUtils.isEmpty(st_num))
        {
            Toast.makeText(this, "Please write student number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(st_name))
        {
            Toast.makeText(this, "Please write student name", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(pa_name))
        {
            Toast.makeText(this, "Please write parent name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pa_tp))
        {
            Toast.makeText(this, "Please write parent contact number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(te_name))
        {
            Toast.makeText(this, "Please write teacher name", Toast.LENGTH_SHORT).show();
    }
        else if (TextUtils.isEmpty(te_tp))
        {
            Toast.makeText(this, "Please write teacher contact number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Add Student");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateNum(st_num, st_name, pa_name, pa_tp, te_name, te_tp);


        }

    }

    private void ValidateNum(final String num, final String name, final String parent_name, final String parentTP, final String TeacherName, final String TeacherTP)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Students").child(num).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("num", num);
                    userdataMap.put("name", name);
                    userdataMap.put("parent_name", parent_name);
                    userdataMap.put("parentTP", parentTP);
                    userdataMap.put("TeacherName", TeacherName);
                    userdataMap.put("TeacherTP", TeacherTP);

                    RootRef.child("Students").child(num).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddStudent.this, "Student account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(AddStudent.this, StaffMenu.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(AddStudent.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(AddStudent.this, "This " + num + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(AddStudent.this, "Please try again using another student ID.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddStudent.this, StaffMenu.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}