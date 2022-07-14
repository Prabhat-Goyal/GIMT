package com.service.gimt;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class ActivityTeacherlistS extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TeacherlistSData> arrayList;
    private FirebaseRecyclerOptions<TeacherlistSData> options;
    private FirebaseRecyclerAdapter<TeacherlistSData,TeacherlistSViewHolder> adapter;
    private DatabaseReference mDatabase,mainDatabase;

    ProgressDialog progressDialog;

    RollSave rollSave;
    Cursor res;
    StringBuffer buffer;

    String branch,sem,course;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlist_s);

        rollSave = new RollSave(this);
        res = rollSave.getAllData();

        buffer = new StringBuffer();
        String phone = null;
        while(res.moveToNext()){
            phone = res.getString(1);
        }


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setTitle("LOADING");
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewN);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList= new ArrayList<TeacherlistSData>();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("students").child(phone);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){

                if (dataSnapshot.exists()) {


                    branch = dataSnapshot.child("branch").getValue().toString();
                    sem = dataSnapshot.child("Semester").getValue().toString();
                    course = dataSnapshot.child("course").getValue().toString();

                    mainDatabase= FirebaseDatabase.getInstance().getReference().child("course").child(course).child("branch").child(branch)
                            .child("semester").child(sem).child("teachers");
                    options= new FirebaseRecyclerOptions.Builder<TeacherlistSData>().setQuery(mainDatabase,TeacherlistSData.class).build();

                    adapter= new FirebaseRecyclerAdapter<TeacherlistSData, TeacherlistSViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull final TeacherlistSViewHolder holder, int position, @NonNull final TeacherlistSData model) {


                            progressDialog.dismiss();
                            holder.heading.setText(model.getHeading());
                            holder.temail.setText(model.getTemail());
                            holder.tphone.setText(model.getTphone());
                        }

                        @NonNull
                        @Override
                        public TeacherlistSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                            return new TeacherlistSViewHolder(LayoutInflater.from(ActivityTeacherlistS.this).inflate(R.layout.item_teacherlist_s_new,viewGroup,false));

                        }
                    };

                    adapter.startListening();

                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }

        });

        initToolbar();




    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Teachers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}


