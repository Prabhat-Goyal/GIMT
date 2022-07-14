package com.service.gimt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class ActivitySProfile extends AppCompatActivity {

    private DatabaseReference mDatabase;

    RollSave rollSave;
    Cursor res;
    StringBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprofile_new);

        rollSave = new RollSave(this);
        res = rollSave.getAllData();

        buffer = new StringBuffer();
        String phone = null;
        while(res.moveToNext()){
            phone = res.getString(1);
        }



        mDatabase = FirebaseDatabase.getInstance().getReference().child("students").child(phone);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){

                if (dataSnapshot.exists()) {

                    CircularImageView imageView=findViewById(R.id.sppic);
                    String url = dataSnapshot.child("profileImgUrl").getValue().toString();
                     Picasso.get().load(url).into((ImageView) findViewById(R.id.sppic));

                    String name = dataSnapshot.child("name").getValue().toString();
                    TextView nameT = findViewById(R.id.spname);
                    nameT.setText(name);

                    String branch = dataSnapshot.child("branch").getValue().toString();
                    TextView branchT = findViewById(R.id.spbranch);
                    branchT.setText(branch);

                    String sem = dataSnapshot.child("Semester").getValue().toString();
                    TextView semT = findViewById(R.id.spsem);
                    semT.setText(sem);

                    String roll = dataSnapshot.child("rollno").getValue().toString();
                    TextView rollT = findViewById(R.id.sproll);
                    rollT.setText(roll);

                    String adate = dataSnapshot.child("adate").getValue().toString();
                    TextView adateT = findViewById(R.id.spadate);
                    adateT.setText(adate);

                    String dob = dataSnapshot.child("dob").getValue().toString();
                    TextView dobT = findViewById(R.id.spdob);
                    dobT.setText(dob);

                    String mno = dataSnapshot.child("mnumber").getValue().toString();
                    TextView mnoT = findViewById(R.id.spmno);
                    mnoT.setText(mno);


                    String email = dataSnapshot.child("email").getValue().toString();
                    TextView emailT = findViewById(R.id.spemail);
                    emailT.setText(email);

                    String fname = dataSnapshot.child("fname").getValue().toString();
                    TextView fnameT = findViewById(R.id.spfname);
                    fnameT.setText(fname);

                    String mname = dataSnapshot.child("mname").getValue().toString();
                    TextView mnameT = findViewById(R.id.spmname);
                    mnameT.setText(mname);

                    String gname = dataSnapshot.child("gname").getValue().toString();
                    TextView gnameT = findViewById(R.id.spgname);
                    gnameT.setText(gname);

                    String pmno = dataSnapshot.child("pmnumber").getValue().toString();
                    TextView pmnoT = findViewById(R.id.sppno);
                    pmnoT.setText(pmno);

                    String ca = dataSnapshot.child("caddress").getValue().toString();
                    TextView caT = findViewById(R.id.spca);
                    caT.setText(ca);

                    String pa = dataSnapshot.child("paddress").getValue().toString();
                    TextView paT = findViewById(R.id.sppa);
                    paT.setText(pa);

                    String bg = dataSnapshot.child("bgroup").getValue().toString();
                    TextView bgT = findViewById(R.id.spbg);
                    bgT.setText(bg);

                    String spm = dataSnapshot.child("spmentor").getValue().toString();
                    TextView spmt = findViewById(R.id.spmt);
                    spmt.setText(spm);
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
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
