package com.bestteam.libvidd;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddPicAfterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button gallary;
    private Button upLoad;
    private Button capture;
    private ImageView img_view;
    private Uri filepath;
    private StorageReference storageReference;
    private static final int PICK_IMGE_REQUST = 1;
    private static final int PICK_CAP_REQUST = 2;
    private static  int PICK;
    Bitmap bit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pic_after);
        storageReference = FirebaseStorage.getInstance().getReference();

        img_view = findViewById(R.id.imageViewPic);
        gallary = (Button) findViewById(R.id.addPicBtn);
        upLoad =  (Button) findViewById(R.id.btn_upload);
        capture = (Button) findViewById(R.id.addPicByCamBtn);
        gallary.setOnClickListener(this);
        upLoad.setOnClickListener(this);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,PICK_CAP_REQUST);
            }
        });

    }

    private void showFileChoser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select img"), PICK_IMGE_REQUST);
        PICK = PICK_IMGE_REQUST;
    }

    private void upLoadFile() {
        if (PICK == PICK_IMGE_REQUST) {
            if (filepath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("uploading...");
                progressDialog.show();

                StorageReference riversRef = storageReference.child("images").child(SignUpActivity.id);

                riversRef.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "file uploaded", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progrss = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("uploading..." + progrss);
                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "UPLOAD FAILD", Toast.LENGTH_LONG).show();
            }

        } else {
            if (bit != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference imagesRef = storageReference.child("images").child(SignUpActivity.id);
                UploadTask uploadTask = imagesRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "UPLOAD cam faild", Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        Toast.makeText(getApplicationContext(), "UPLOAD cam SUCCSSES!", Toast.LENGTH_LONG).show();
                    }
                });
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMGE_REQUST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == PICK_IMGE_REQUST) {
                filepath = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    img_view.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            bit = (Bitmap) data.getExtras().get("data");
            img_view.setImageBitmap(bit);
        }
    }








    @Override
    public void onClick(View view) {
        if (view == gallary) {
            showFileChoser();
        } else if (view == upLoad) {

            upLoadFile();

        }
    }
}

