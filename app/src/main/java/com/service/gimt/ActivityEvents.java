package com.service.gimt;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ActivityEvents extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<EventsData> arrayList;
    private FirebaseRecyclerOptions<EventsData> options;
    private FirebaseRecyclerAdapter<EventsData,EventsViewHolder> adapter;
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
        setContentView(R.layout.activity_events);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("events");


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setTitle("LOADING");
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewN);
        bottom_sheet = findViewById(R.id.bottom_sheet);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList= new ArrayList<EventsData>();
        mBehavior = BottomSheetBehavior.from(bottom_sheet);

        options= new FirebaseRecyclerOptions.Builder<EventsData>().setQuery(mDatabase,EventsData.class).build();

        adapter= new FirebaseRecyclerAdapter<EventsData, EventsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventsViewHolder holder, int position, @NonNull final EventsData model) {


                progressDialog.dismiss();
                holder.heading.setText(model.getHeading());
                holder.date.setText(model.getDate());
                holder.itemView.findViewById(R.id.bt_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }

                        final View view = getLayoutInflater().inflate(R.layout.sheet_events, null);
                        ((TextView) view.findViewById(R.id.name)).setText(model.getHeading());
                        ((TextView) view.findViewById(R.id.date)).setText(model.getDate());
                        ((TextView) view.findViewById(R.id.details)).setText(model.getDetails());
                        (view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mBottomSheetDialog.dismiss();
                            }
                        });

                        mBottomSheetDialog = new BottomSheetDialog(ActivityEvents.this);
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
            public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new EventsViewHolder(LayoutInflater.from(ActivityEvents.this).inflate(R.layout.item_events,viewGroup,false));
            }
        };

        recyclerView.setAdapter(adapter);

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}


