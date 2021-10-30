package com.example.littleminds;

import android.view.View;
import android.content.Context;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

View view;

    public Holder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setView(Context context,String TeacherName, String TeacherTP, String name, String num, String parent_name, String parentTP)
    {
        TextView TeacherNam = view.findViewById(R.id.TeacherName);
        TextView TeacherT = view.findViewById(R.id.TeacherTP);
        TextView nam = view.findViewById(R.id.name);
        TextView nu = view.findViewById(R.id.num);
        TextView parentNam = view.findViewById(R.id.parent_name);
        TextView parentT = view.findViewById(R.id.parentTP);



        TeacherNam.setText(TeacherName);
        TeacherT.setText(TeacherTP);
        nam.setText(name);
        nu.setText(num);
        parentNam.setText(parent_name);
        parentT.setText(parentTP);


    }
}
