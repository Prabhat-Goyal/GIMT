package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ExamSViewHolder extends RecyclerView.ViewHolder {

    public TextView heading;
    public ExamSViewHolder(@NonNull View itemView) {
        super(itemView);

        heading=itemView.findViewById(R.id.exam_heading);

    }
}
