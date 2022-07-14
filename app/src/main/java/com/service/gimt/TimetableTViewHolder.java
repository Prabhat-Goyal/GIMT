package com.service.gimt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TimetableTViewHolder extends RecyclerView.ViewHolder {

    public TextView week, s1, s2, s3, s4, s5, s6, s7, s8, s9, r1, r2, r3, r4, r5, r6, r7, r8, r9;
    public TimetableTViewHolder(@NonNull View itemView) {
        super(itemView);

        week=itemView.findViewById(R.id.week);
        s1=itemView.findViewById(R.id.s1);
        s2=itemView.findViewById(R.id.s2);
        s3=itemView.findViewById(R.id.s3);
        s4=itemView.findViewById(R.id.s4);
        s5=itemView.findViewById(R.id.s5);
        s6=itemView.findViewById(R.id.s6);
        s7=itemView.findViewById(R.id.s7);
        s8=itemView.findViewById(R.id.s8);
        s9=itemView.findViewById(R.id.s9);

        r1=itemView.findViewById(R.id.r1);
        r2=itemView.findViewById(R.id.r2);
        r3=itemView.findViewById(R.id.r3);
        r4=itemView.findViewById(R.id.r4);
        r5=itemView.findViewById(R.id.r5);
        r6=itemView.findViewById(R.id.r6);
        r7=itemView.findViewById(R.id.r7);
        r8=itemView.findViewById(R.id.r8);
        r9=itemView.findViewById(R.id.r9);

    }
}
