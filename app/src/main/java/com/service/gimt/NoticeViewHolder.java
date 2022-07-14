package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NoticeViewHolder extends RecyclerView.ViewHolder {

    public TextView heading,date;
    public NoticeViewHolder(@NonNull View itemView) {
        super(itemView);

        heading=itemView.findViewById(R.id.m_heading);
        date=itemView.findViewById(R.id.m_date);
    }
}
