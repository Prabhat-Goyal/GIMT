package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TeacherlistSViewHolder extends RecyclerView.ViewHolder {

    public TextView heading,temail,tphone;
    public TeacherlistSViewHolder(@NonNull View itemView) {
        super(itemView);

        heading=itemView.findViewById(R.id.mentee);
        temail=itemView.findViewById(R.id.bt_emailt);
        tphone=itemView.findViewById(R.id.bt_phonet);

    }
}
