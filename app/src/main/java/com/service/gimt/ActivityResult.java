package com.service.gimt;

import android.app.DownloadManager;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class ActivityResult extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ResultSData> arrayList;
    private FirebaseRecyclerOptions<ResultSData> options;
    private FirebaseRecyclerAdapter<ResultSData,ResultSViewHolder> adapter;
    private DatabaseReference mDatabase;

    RollSave rollSave;
    Cursor res;
    StringBuffer buffer;

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
        setContentView(R.layout.activity_result_s);

        rollSave = new RollSave(this);
        res = rollSave.getAllData();

        buffer = new StringBuffer();
        String phone = null;
        while(res.moveToNext()){
            phone = res.getString(1);
        }

        mDatabase= FirebaseDatabase.getInstance().getReference().child("students").child(phone).child("result");


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.setTitle("LOADING");
        progressDialog.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewN);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList= new ArrayList<ResultSData>();
        options= new FirebaseRecyclerOptions.Builder<ResultSData>().setQuery(mDatabase,ResultSData.class).build();

        adapter= new FirebaseRecyclerAdapter<ResultSData, ResultSViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ResultSViewHolder holder, int position, @NonNull final ResultSData model) {


                progressDialog.dismiss();
                holder.exam.setText(model.getExam());
                holder.itemView.findViewById(R.id.bt_downloadr).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        downloadFile(ActivityResult.this,model.getExam(),".pdf",DIRECTORY_DOWNLOADS,model.getPdf());

                        Toast.makeText(ActivityResult.this, "Downloading....", Toast.LENGTH_SHORT).show();


                    }
                });
            }

            @NonNull
            @Override
            public ResultSViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new ResultSViewHolder(LayoutInflater.from(ActivityResult.this).inflate(R.layout.item_result_new,viewGroup,false));
            }
        };

        recyclerView.setAdapter(adapter);

        initToolbar();
    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadmanager.enqueue(request);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}


