package com.service.gimt;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoticeTActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<NoticeData> arrayList;
    private FirebaseRecyclerOptions<NoticeData> options;
    private FirebaseRecyclerAdapter<NoticeData,NoticeViewHolder> adapter;
    private DatabaseReference mDatabase;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

    ProgressDialog progressDialog;


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_tactivity);



        mDatabase= FirebaseDatabase.getInstance().getReference().child("teachers").child("notice");


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setTitle("LOADING");
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewN);
        bottom_sheet = findViewById(R.id.bottom_sheet);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList= new ArrayList<NoticeData>();
        mBehavior = BottomSheetBehavior.from(bottom_sheet);

        options= new FirebaseRecyclerOptions.Builder<NoticeData>().setQuery(mDatabase,NoticeData.class).build();

        adapter= new FirebaseRecyclerAdapter<NoticeData, NoticeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull final NoticeData model) {


                progressDialog.dismiss();
                holder.heading.setText(model.getHeading());
                holder.date.setText(model.getDate());
                holder.itemView.findViewById(R.id.bt_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }

                        final View view = getLayoutInflater().inflate(R.layout.sheet_notice, null);
                        ((TextView) view.findViewById(R.id.name)).setText(model.getHeading());
                        ((TextView) view.findViewById(R.id.date)).setText(model.getDate());
                        ((TextView) view.findViewById(R.id.details)).setText(model.getDetails());
                        (view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        mBottomSheetDialog = new BottomSheetDialog(NoticeTActivity.this);
                        mBottomSheetDialog.setContentView(view);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        }

                        mBottomSheetDialog.show();
                        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                mBottomSheetDialog = null;
                            }
                        });

                    }
                });
            }

            @NonNull
            @Override
            public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new NoticeViewHolder(LayoutInflater.from(NoticeTActivity.this).inflate(R.layout.item_notice,viewGroup,false));
            }
        };

        recyclerView.setAdapter(adapter);

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}


