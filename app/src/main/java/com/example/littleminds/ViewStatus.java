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


public class ViewStatus extends AppCompatActivity {

    RecyclerView recycleView;
    DatabaseReference ref;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);


        recycleView = findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);

        recycleView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Students_Status");
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Status,Holder2> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Status, Holder2>(
                        Status.class,
                        R.layout.data2,
                        Holder2.class,
                        ref
                ) {
                    @Override
                    protected void populateViewHolder(Holder2 holder2, Status status, int i) {
                        holder2.setView(getApplicationContext(), status.getNum(), status.getStatus1(), status.getStatus2(), status.getStatus3(), status.getStatus4());

                    }
                };

        recycleView.setAdapter(firebaseRecyclerAdapter);
    }
}