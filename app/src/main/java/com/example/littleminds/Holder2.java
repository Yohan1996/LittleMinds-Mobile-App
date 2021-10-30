package com.example.littleminds;

import android.view.View;
import android.content.Context;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder2 extends RecyclerView.ViewHolder{

    View view;

    public Holder2(@NonNull View itemView) {
        super(itemView);
        view = itemView;

    }

    public void setView(Context context,String num, String status1, String status2, String status3, String status4)
    {
        TextView number = view.findViewById(R.id.num);
        TextView Status1 = view.findViewById(R.id.status1);
        TextView Status2 = view.findViewById(R.id.status2);
        TextView Status3 = view.findViewById(R.id.status3);
        TextView Status4 = view.findViewById(R.id.status4);



        number.setText(num);
        Status1.setText(status1);
        Status2.setText(status2);
        Status3.setText(status3);
        Status4.setText(status4);


    }

}
