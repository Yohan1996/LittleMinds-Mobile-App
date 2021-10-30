package com.example.littleminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class RegisterStaff extends AppCompatActivity {

    private Button register_btn;
    private EditText s_name, s_tp, s_password;
    private ProgressDialog loadingBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_staff);

        register_btn= (Button) findViewById(R.id.btn);
        s_name= (EditText) findViewById(R.id.staff_name);
        s_tp= (EditText) findViewById(R.id.staff_tp);
        s_password= (EditText) findViewById(R.id.staff_password);
        loadingBar1 = new ProgressDialog(this);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register2();
            }
        });
    }
    private void register2()
    {
        String name = s_name.getText().toString();
        String tp = s_tp.getText().toString();
        String password = s_password.getText().toString();


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
            loadingBar1.setTitle("Register Account");
            loadingBar1.setMessage("Please wait, while we are checking the credentials.");
            loadingBar1.setCanceledOnTouchOutside(false);
            loadingBar1.show();

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

                if (!(dataSnapshot.child("Staff").child(name).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name", name);
                    userdataMap.put("phone", tp);
                    userdataMap.put("password", password);

                    RootRef.child("Staff").child(name).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterStaff.this, "Staff account registered.", Toast.LENGTH_SHORT).show();
                                        loadingBar1.dismiss();

                                        Intent intent = new Intent(RegisterStaff.this, StaffMenu.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar1.dismiss();
                                        Toast.makeText(RegisterStaff.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                } else {
                    Toast.makeText(RegisterStaff.this, "This " + name + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar1.dismiss();
                    Toast.makeText(RegisterStaff.this, "Please try again using another name.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterStaff.this, StaffMenu.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}