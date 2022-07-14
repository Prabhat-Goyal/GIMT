package com.service.gimt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ActivityLoginS extends AppCompatActivity {

    private FloatingActionButton fab;
    private TextInputEditText rno;
    private DatabaseReference mDatabase;

    RollSave rollSave;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_s);

        fab= findViewById(R.id.fab);
        rno=(TextInputEditText)findViewById(R.id.rno);


        rollSave = new RollSave(this);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rno.getText().toString().isEmpty()){
                    Toast.makeText(ActivityLoginS.this, "enter your roll no", Toast.LENGTH_SHORT).show();
                }else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("students").child(rno.getText().toString());

                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.exists()){

                                Boolean result = rollSave.insertData(rno.getText().toString());

                                if(result) {

                                    Intent intent = new Intent(ActivityLoginS.this, HomeActivityS.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(ActivityLoginS.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(ActivityLoginS.this, "Invalid Roll No", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }
        });

    }

}
