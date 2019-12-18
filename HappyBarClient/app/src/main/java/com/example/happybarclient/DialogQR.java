package com.example.happybarclient;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogQR{

    Context context;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference myRef;
    StorageReference mStorageRef;
    Button ok;
    ImageView imageDialogQR;

    public DialogQR(Context context) {
        this.context = context;
    }

    public Dialog generateDialog(final String nameDrink){

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_qr);

        ok = dialog.findViewById(R.id.aceptar);
        imageDialogQR = dialog.findViewById(R.id.image_dialog_qr);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyyhhmmss");
        final String timeStamp = formatDate.format(new Date());

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Images");

        String content = "[" +
                "{userId: " + user.getUid() + "}, " +
                "{timestamp: " + timeStamp + "}, " +
                "{name: " + nameDrink + "}" +
                "]";

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 500, 500);
            imageDialogQR.setImageBitmap(bitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            final StorageReference mountainsRef = mStorageRef.child(timeStamp);

            UploadTask uploadTask = mountainsRef.putBytes(data);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    myRef.child(user.getUid()).child(timeStamp).setValue(timeStamp);
                    myRef.child(user.getUid()).child(timeStamp).child("nameDrink").setValue(nameDrink);
                    myRef.child(user.getUid()).child(timeStamp).child("used").setValue("no");

                    mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            myRef.child(user.getUid()).child(timeStamp).child("image64").setValue(String.valueOf(uri));
                        }
                    });
                    Toast.makeText(context, "Código QR guardado", Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
        }
        return dialog;
    }
}
