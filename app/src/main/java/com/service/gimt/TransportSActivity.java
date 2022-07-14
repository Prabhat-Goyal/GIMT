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

public class TransportSActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    RollSave rollSave;
    Cursor res;
    StringBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_sactivity);

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

                    String busno = dataSnapshot.child("busno").getValue().toString();
                    TextView busnoT = findViewById(R.id.busno);
                    busnoT.setText(busno);

                    String broute = dataSnapshot.child("broute").getValue().toString();
                    TextView brouteT = findViewById(R.id.broute);
                    brouteT.setText(broute);

                    String bustime = dataSnapshot.child("bustime").getValue().toString();
                    TextView bustimeT = findViewById(R.id.bustime);
                    bustimeT.setText(bustime);

                    String busfees = dataSnapshot.child("busfees").getValue().toString();
                    TextView busfeesT = findViewById(R.id.busfees);
                    busfeesT.setText(busfees);


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
        getSupportActionBar().setTitle("Transport");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
