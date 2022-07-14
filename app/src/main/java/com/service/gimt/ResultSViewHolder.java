package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ResultSViewHolder extends RecyclerView.ViewHolder {

    public TextView exam;
    public ResultSViewHolder(@NonNull View itemView) {
        super(itemView);

        exam=itemView.findViewById(R.id.exam_heading);

    }
}
