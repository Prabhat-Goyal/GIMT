package com.service.gimt;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivityExamS extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ExamSData> arrayList;
    private FirebaseRecyclerOptions<ExamSData> options;
    private FirebaseRecyclerAdapter<ExamSData,ExamSViewHolder> adapter;
    private DatabaseReference mDatabase,mainDatabase;


    ProgressDialog progressDialog;
    String branch,sem,course;

    RollSave rollSave;
    Cursor res;
    StringBuffer buffer;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_s);

        rollSave = new RollSave(this);
        res = rollSave.getAllData();

        buffer = new StringBuffer();
        String phone = null;
        while(res.moveToNext()){
            phone = res.getString(1);
        }




        progressDialog = new ProgressDialog(ActivityExamS.this);
        progressDialog.setMessage("please wait...");
        progressDialog.setTitle("LOADING");
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewN);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityExamS.this));
        arrayList= new ArrayList<ExamSData>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("students").child(phone);

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
        //Toast.makeText(this, "fetching data...", Toast.LENGTH_SHORT).show();

        mainDatabase= FirebaseDatabase.getInstance().getReference().child("course").child(course).child("branch").child(branch)
                .child("semester").child(sem).child("exams");


        options= new FirebaseRecyclerOptions.Builder<ExamSData>().setQuery(mainDatabase,ExamSData.class).build();

        adapter = new FirebaseRecyclerAdapter<ExamSData, ExamSViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ExamSViewHolder holder, int position, @NonNull ExamSData model) {
                progressDialog.dismiss();
                holder.heading.setText(model.getHeading());
                final String id = model.getId();
                holder.itemView.findViewById(R.id.bt_viewex).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ActivityExamS.this,ActivityExamSchedule.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ExamSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new ExamSViewHolder(LayoutInflater.from(ActivityExamS.this).inflate(R.layout.item_exam_new,viewGroup,false));
            }
        };

        adapter.startListening();


        recyclerView.setAdapter(adapter);




    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!= null){
            adapter.startListening();
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Exam");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}


