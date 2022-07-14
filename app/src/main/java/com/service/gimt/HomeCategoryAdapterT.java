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

public class HomeCategoryAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeCategoryData> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, HomeCategoryData obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public HomeCategoryAdapterT(Context context, List<HomeCategoryData> items) {
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
                                Intent intent = new Intent(ctx,ActivityTProfile.class);
                                ctx.startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(ctx, AttendanceActivityT.class);
                                ctx.startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(ctx,ResultTeacher.class);
                                ctx.startActivity(intent3);
                                break;
                            case 3:
                                Intent intent4 = new Intent(ctx,AssignmentTeacher.class);
                                ctx.startActivity(intent4);
                                break;
                            case 4:
                                Intent intent5 = new Intent(ctx, HolidayActivityT.class);
                                ctx.startActivity(intent5);
                                break;
                            case 5:
                                Intent intent6 = new Intent(ctx,ActivityTimetableT.class);
                                ctx.startActivity(intent6);
                                break;
                            case 6:
                                Intent intent7 = new Intent(ctx,NoticeTActivity.class);
                                ctx.startActivity(intent7);
                                break;

                                case 7:
                                    Intent intent8 = new Intent(ctx,MenteesTActivity.class);
                                    ctx.startActivity(intent8);
                                    break;

                            case 8:
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