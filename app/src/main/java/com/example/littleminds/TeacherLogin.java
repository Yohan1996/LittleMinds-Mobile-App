package com.example.littleminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.littleminds.Model.Teachers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherLogin extends AppCompatActivity {

    //Variables
    private EditText InputName, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;

    //Database Table
    private String parentDbName = "Teachers";
    private CheckBox chkBoxRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        LoginButton = (Button) findViewById(R.id.option_button);
        InputName = (EditText) findViewById(R.id.login_name);
        InputPassword = (EditText) findViewById(R.id.login_password);
        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });

            }
      private void LoginUser()
      {
          String name = InputName.getText().toString();
          String password = InputPassword.getText().toString();

          if (TextUtils.isEmpty(name))
          {
              Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
          }
          else if (TextUtils.isEmpty(password))
          {
              Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
          }
          else
          {

              loadingBar.setTitle("Login Account");
              loadingBar.setMessage("Please wait, while we are checking the credentials.");
              loadingBar.setCanceledOnTouchOutside(false);
              loadingBar.show();

              AllowAccessToAccount(name, password);
          }

      }
private void AllowAccessToAccount(final String name, final String password)
{
    final DatabaseReference RootRef;
    RootRef = FirebaseDatabase.getInstance().getReference();

    RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
            if (datasnapshot.child(parentDbName).child(name).exists()) {
                Teachers usersData = datasnapshot.child(parentDbName).child(name).getValue(Teachers.class);

                if (usersData.getName().equals(name)) {
                    if (usersData.getPassword().equals(password)) {
                        Toast.makeText(TeacherLogin.this, "Welcome , you are logged in Successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Intent intent = new Intent(TeacherLogin.this, TeacherMenu.class);
                        startActivity(intent);
                    }
                    else
                    {
                        loadingBar.dismiss();
                        Toast.makeText(TeacherLogin.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {

                Toast.makeText(TeacherLogin.this, "Account with this " + name + " name do not exists.", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}


}