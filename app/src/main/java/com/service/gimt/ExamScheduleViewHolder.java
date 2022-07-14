package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ExamScheduleViewHolder extends RecyclerView.ViewHolder {

    public TextView name,room,date,stime,etime;
    public ExamScheduleViewHolder(@NonNull View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.examsub_heading);
        room=itemView.findViewById(R.id.exam_room);
        date=itemView.findViewById(R.id.exam_date);
        stime=itemView.findViewById(R.id.stime);
        etime=itemView.findViewById(R.id.etime);

    }
}
