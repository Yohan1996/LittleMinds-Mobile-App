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

public class RegisterTeacher extends AppCompatActivity {

    private Button register_btn;
    private EditText t_name, t_tp, t_password;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        register_btn= (Button) findViewById(R.id.btn);
        t_name= (EditText) findViewById(R.id.teacher_name);
        t_tp= (EditText) findViewById(R.id.teacher_tp);
        t_password= (EditText) findViewById(R.id.teacher_password);
        loadingBar = new ProgressDialog(this);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register()
    {
        String name = t_name.getText().toString();
        String tp = t_tp.getText().toString();
        String password = t_password.getText().toString();


        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(tp))
        {
            Toast.makeText(this, "Please write phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Register Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateName(name, tp, password);


        }

}

    private void ValidateName(final String name, final String tp, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Teachers").child(name).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name", name);
                    userdataMap.put("phone", tp);
                    userdataMap.put("password", password);

                    RootRef.child("Teachers").child(name).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterTeacher.this, "Staff account registered.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterTeacher.this, StaffMenu.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterTeacher.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(RegisterTeacher.this, "This " + name + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterTeacher.this, "Please try again using another phone name.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterTeacher.this, StaffMenu.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}