package com.service.gimt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryAdapterS extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeCategoryData> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, HomeCategoryData obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public HomeCategoryAdapterS(Context context, List<HomeCategoryData> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            title = (TextView) v.findViewById(R.id.title);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            HomeCategoryData p = items.get(position);
            view.title.setText(p.title);
            view.image.setImageDrawable(p.imageDrw);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        switch(position){
                            case 0:
                                Intent intent = new Intent(ctx,ActivitySProfile.class);
                                ctx.startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(ctx, AttendanceActivityS.class);
                                ctx.startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(ctx,ActivityExamS.class);
                                ctx.startActivity(intent3);
                                break;
                            case 3:
                                Intent intent4 = new Intent(ctx,ActivityResult.class);
                                ctx.startActivity(intent4);
                                break;
                            case 4:
                                Intent intent5 = new Intent(ctx,ActivityTeacherlistS.class);
                                ctx.startActivity(intent5);
                                break;
                            case 5:
                                Intent intent6 = new Intent(ctx,ActivityAssignmentsS.class);
                                ctx.startActivity(intent6);
                                break;
                            case 6:
                                Intent intent7 = new Intent(ctx, HolidayActivityS.class);
                                ctx.startActivity(intent7);
                                break;
                            case 7:
                                Intent intent8 = new Intent(ctx,ActivityEvents.class);
                                ctx.startActivity(intent8);
                                break;
                            case 8:
                                Intent intent9 = new Intent(ctx, ActivityNoticeS.class);
                                ctx.startActivity(intent9);
                                break;
                            case 9:
                                Intent intent10 = new Intent(ctx,ActivityInstituteP.class);
                                ctx.startActivity(intent10);
                                break;
                            case 10:
                                Intent intent11 = new Intent(ctx,ActivityTimetableS.class);
                                ctx.startActivity(intent11);
                                break;
                            case 11:
                                Intent intent12 = new Intent(ctx, ActivityFeesS.class);
                                ctx.startActivity(intent12);
                                break;
                            case 12:
                                Intent intent13 = new Intent(ctx, HostelSActivity.class);
                                ctx.startActivity(intent13);
                                break;
                            case 13:
                                Intent intent14 = new Intent(ctx, TransportSActivity.class);
                                ctx.startActivity(intent14);
                                break;
                            case 14:
                                Intent intent15 = new Intent(ctx, PendingActivity.class);
                                ctx.startActivity(intent15);
                                break;
                            case 15:
                                Intent intent16 = new Intent(ctx, PreloginActivity.class);
                                ctx.startActivity(intent16);
                                break;

                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}