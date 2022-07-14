package com.service.gimt;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityExamSchedule extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ExamScheduleData> arrayList;
    private FirebaseRecyclerOptions<ExamScheduleData> options;
    private FirebaseRecyclerAdapter<ExamScheduleData,ExamScheduleViewHolder> adapter;
    private DatabaseReference mDatabase,mainDatabase;


    String id;


    ProgressDialog progressDialog;
    String branch,sem,course;

    @Override
    protected void onStart() {
        super.onStart();
        //adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_schedule);



        id = getIntent().getExtras().get("id").toString();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setTitle("LOADING");
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewN);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList= new ArrayList<ExamScheduleData>();



        mDatabase = FirebaseDatabase.getInstance().getReference().child("students").child("170310007038");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){

                if (dataSnapshot.exists()) {


                    branch = dataSnapshot.child("branch").getValue().toString();
                    sem = dataSnapshot.child("Semester").getValue().toString();
                    course = dataSnapshot.child("course").getValue().toString();
                    fetchData();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }

        });

        initToolbar();



    }


    private void fetchData(){


        mainDatabase= FirebaseDatabase.getInstance().getReference().child("course").child(course).child("branch").child(branch)
                .child("semester").child(sem).child("exams").child(id).child("subjects");

        options= new FirebaseRecyclerOptions.Builder<ExamScheduleData>().setQuery(mainDatabase,ExamScheduleData.class).build();

        adapter = new FirebaseRecyclerAdapter<ExamScheduleData, ExamScheduleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ExamScheduleViewHolder holder, int position, @NonNull ExamScheduleData model) {
                progressDialog.dismiss();
                holder.name.setText(model.getName());
                holder.date.setText(model.getDate());
                holder.room.setText(model.getRoom());
                holder.stime.setText(model.getStime());
                holder.etime.setText(model.getEtime());

            }

            @NonNull
            @Override
            public ExamScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new ExamScheduleViewHolder(LayoutInflater.from(ActivityExamSchedule.this).inflate(R.layout.item_exam_schedule_new,viewGroup,false));
            }
        };



        adapter.startListening();
        recyclerView.setAdapter(adapter);





    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Exam Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}


