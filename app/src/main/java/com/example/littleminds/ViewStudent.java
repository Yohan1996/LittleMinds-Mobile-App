package com.example.littleminds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ViewStudent extends AppCompatActivity {

    RecyclerView recycleView;
    DatabaseReference ref;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);

        recycleView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Students");
}

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Students,Holder> firebaseRecyclerAdapter =
    new FirebaseRecyclerAdapter<Students, Holder>(
            Students.class,
            R.layout.data,
            Holder.class,
            ref
    ) {
        @Override
        protected void populateViewHolder(Holder holder, Students students, int i) {
            holder.setView(getApplicationContext(), students.getTeacherName(), students.getTeacherTP(), students.getName(), students.getNum(), students.getParentName(), students.getParentTP());

        }
    };

        recycleView.setAdapter(firebaseRecyclerAdapter);
    }
}