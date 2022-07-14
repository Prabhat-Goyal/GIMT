package com.service.gimt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ResultTeacher extends AppCompatActivity {



    ImageView uploadImage;
    ProgressBar pb;
    TextView pdfText;
    Button btnSubmit;
    Uri selectedPdf;
    private static int REQUESTCODE_PICK_Pdf = 4;
    StorageReference storageReference;
    ProgressDialog pg;
    EditText rollno,exam;
    StorageReference filePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_teacher_new);

        uploadImage = findViewById(R.id.btn_upload);
        pb = findViewById(R.id.pb_upload);
        pdfText = findViewById(R.id.text_upload_pdf);
        btnSubmit = findViewById(R.id.btn_submit_pdf);
        rollno = findViewById(R.id.rollnoup);
        exam = findViewById(R.id.examup);
        storageReference = FirebaseStorage.getInstance().getReference();
        pb.setVisibility(View.GONE);
        pg = new ProgressDialog(this);
        pg.setMessage("loading...");





        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), REQUESTCODE_PICK_Pdf);
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPdf != null && !rollno.getText().toString().isEmpty() && !exam.getText().toString().isEmpty()){
                    pb.setVisibility(View.VISIBLE);

                    long idLong = System.currentTimeMillis()/1000;
                    final String id = String.valueOf(idLong);



                    filePath = storageReference.child("exam result").child(rollno.getText().toString()).child(exam.getText().toString()+".pdf");

                    filePath.putFile(selectedPdf).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    HashMap map = new HashMap();
                                    Uri downloadUrl = uri;
                                    map.put("exam",exam.getText().toString().toUpperCase());
                                    map.put("pdf",downloadUrl.toString());

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("students")
                                            .child(rollno.getText().toString())
                                            .child("result")
                                            .child(id)
                                            .updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            Toast.makeText(ResultTeacher.this, "upload successful", Toast.LENGTH_SHORT).show();
                                            pb.setVisibility(View.GONE);
                                            exam.setText("");
                                            rollno.setText("");
                                            selectedPdf = null;
                                            pdfText.setText("click above to upload");

                                        }
                                    });

                                }
                            });

                        }
                    });

                }
                else {
                    Toast.makeText(ResultTeacher.this, "Enter Details first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initToolbar();




    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE_PICK_Pdf && resultCode == RESULT_OK
                && null != data) {

            selectedPdf = data.getData();
            Cursor returnCursor =
                    getContentResolver().query(selectedPdf, null, null, null, null);

            returnCursor.moveToFirst();
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);


            pdfText.setText(returnCursor.getString(nameIndex));
            if (selectedPdf.getLastPathSegment().endsWith("pdf")) {
                pdfText.setText(selectedPdf.getLastPathSegment());
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Invalid file type", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upload Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
