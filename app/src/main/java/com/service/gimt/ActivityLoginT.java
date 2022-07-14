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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLoginT extends AppCompatActivity {

    private FloatingActionButton fab;
    private TextInputEditText idno;
    private DatabaseReference mDatabase;

    IdSave idSave;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_t);

        fab= (FloatingActionButton)findViewById(R.id.fab);
        idno=(TextInputEditText)findViewById(R.id.idno);


        idSave = new IdSave(this);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idno.getText().toString().isEmpty()){
                    Toast.makeText(ActivityLoginT.this, "enter your id no", Toast.LENGTH_SHORT).show();
                }else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("teachers").child(idno.getText().toString());

                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.exists()){

                                Boolean result = idSave.insertData(idno.getText().toString());

                                if(result) {

                                    Intent intent = new Intent(ActivityLoginT.this, HomeActivityT.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(ActivityLoginT.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(ActivityLoginT.this, "Invalid Id No", Toast.LENGTH_SHORT).show();
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
