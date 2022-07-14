package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AssignmentSViewHolder extends RecyclerView.ViewHolder {

    public TextView heading,sdate;
    public AssignmentSViewHolder(@NonNull View itemView) {
        super(itemView);

        heading=itemView.findViewById(R.id.exam_heading);
        sdate=itemView.findViewById(R.id.m_date);

    }
}
