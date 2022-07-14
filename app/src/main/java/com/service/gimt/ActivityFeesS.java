package com.service.gimt;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ActivityFeesS extends AppCompatActivity {

    private ImageView imageView;

    private TextView statusView;

    private FloatingActionButton payButton;
    TextView  fieldSem;

    private EditText fieldAmount;

    RollSave rollSave;
    Cursor res;
    StringBuffer buffer;

    String rollNo = null;
    int sem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees_s);



        rollSave = new RollSave(this);
        res = rollSave.getAllData();

        buffer = new StringBuffer();
        while(res.moveToNext()){
            rollNo = res.getString(1);
        }

        payButton = findViewById(R.id.button_pay);
        fieldAmount = findViewById(R.id.field_amount);
        fieldSem = findViewById(R.id.field_sem);


        FirebaseDatabase.getInstance().getReference().child("payments").child(rollNo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    sem = 1;
                    for(int i=1; i<9;i++) {
                        if (dataSnapshot.child("sem_"+i).getValue().toString().equals("paid")) {
                            sem +=1;
                            continue;
                        } else {
                            break;
                        }
                    }
                    fieldSem.setText("SEMESTER: "+sem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(fieldAmount.getText().toString())){
                    Toast.makeText(ActivityFeesS.this, "Enter the amount", Toast.LENGTH_SHORT).show();
                }else{
                    Uri uri = Uri.parse("upi://pay?pa=8876545346@ybl&pn=fees%20collection&tn=Fees%20GIMT&am="+
                            fieldAmount.getText().toString()+"&cu=INR");

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivityForResult(intent, 1421);
                }

            }
        });

        initToolbar();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1421)
        {
            if (resultCode==RESULT_OK)
            {
                assert data != null;
                Log.e("data","response "+data.getStringExtra("response"));

                Toast.makeText(this, "Transaction Successful", Toast.LENGTH_LONG).show();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("payments").child(rollNo).child("sem_"+sem);

                ref.setValue("paid");
                startActivity(new Intent(ActivityFeesS.this,paymentSuccessActivity.class));
                finish();



            }
            else{
                Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fees");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
