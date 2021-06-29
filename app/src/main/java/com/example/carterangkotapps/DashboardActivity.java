package com.example.carterangkotapps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import pl.aprilapps.easyphotopicker.EasyImage;

public class DashboardActivity extends AppCompatActivity {

    TextView akun, tanggal, ucapan;
    private ImageView setImage;
    public static final int REQUEST_CODE_CAMERA = 001;
    public static final int REQUEST_CODE_GALLERY = 002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        akun = findViewById( R.id.textAkun );
        tanggal = findViewById( R.id.tvTanggal );
        ucapan = findViewById( R.id.tvUcapan );
        setImage = findViewById( R.id.avatar );

        Date date = new Date();
        String dateFormat = DateFormat.getDateTimeInstance( DateFormat.MEDIUM , DateFormat.SHORT ).format( date );
        tanggal.setText( dateFormat );
        gretting();

        setImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRequestImage();
            }
        } );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String userEmail = user.getDisplayName();
            akun.setText( userEmail );
        } else {
            // No user is signed in
        }
    }

    private void setRequestImage() {
        CharSequence[] item = {"Kamera" , "Galeri"};
        AlertDialog.Builder request = new AlertDialog.Builder( this )
                .setTitle( "Add Image" )
                .setItems( item , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface , int i) {
                        switch (i) {
                            case 0:
                                //Membuka Kamera Untuk Mengambil Gambar
                                EasyImage.openCamera( DashboardActivity.this , REQUEST_CODE_CAMERA );
                                break;
                            case 1:
                                //Membuaka Galeri Untuk Mengambil Gambar
                                EasyImage.openGallery( DashboardActivity.this , REQUEST_CODE_GALLERY );
                                break;
                        }
                    }
                } );
        request.create();
        request.show();
    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        super.onActivityResult( requestCode , resultCode , data );
        EasyImage.handleActivityResult( requestCode , resultCode , data , this , new EasyImage.Callbacks() {

            @Override
            public void onImagePickerError(Exception e , EasyImage.ImageSource source , int type) {
                //Method Ini Digunakan Untuk Menghandle Error pada Image
            }

            @Override
            public void onImagePicked(File imageFile , EasyImage.ImageSource source , int type) {
                //Method Ini Digunakan Untuk Menghandle Image
                switch (type) {
                    case REQUEST_CODE_CAMERA:
                        Glide.with( DashboardActivity.this )
                                .load( imageFile )
                                .centerCrop()
                                .diskCacheStrategy( DiskCacheStrategy.ALL )
                                .into( setImage );
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with( DashboardActivity.this )
                                .load( imageFile )
                                .centerCrop()
                                .diskCacheStrategy( DiskCacheStrategy.ALL )
                                .into( setImage );
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source , int type) {
                //Batalkan penanganan, Anda mungkin ingin menghapus foto yang diambil jika dibatalkan
            }
        } );
    }

    private void gretting() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get( Calendar.HOUR_OF_DAY );
        String gretting = null;
        if (hour >= 0 && hour < 12) {
            gretting = "Good Morning, Sir";
        } else if (hour >= 12 && hour < 15) {
            gretting = "Good Afternoon, Sir";
        } else if (hour >= 15 && hour < 18) {
            gretting = "Good Afternoon, Sir";
        } else if (hour >= 18 && hour < 24) {
            gretting = "Good evening, Sir";
        }

        ucapan.setText( gretting + "!" );

    }

    public void driver(View view) {
        Intent driver = new Intent( DashboardActivity.this , HomeActivity.class );
        startActivity( driver );
    }

    public void feedback(View view) {
        Intent feed = new Intent( DashboardActivity.this , FeedbackActivity.class );
        startActivity( feed );
    }

    public void car(View view) {
        Intent car = new Intent( DashboardActivity.this , HomeActivity.class );
        startActivity( car );
    }

    public void out(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity( new Intent(this, LoginActivity.class ) );
    }

    public void feed(View view) {
        Intent feedback = new Intent( DashboardActivity.this , FeedbackActivity.class );
        startActivity( feedback );
    }

    public void tvFeed(View view) {
        Intent feedbackTv = new Intent( DashboardActivity.this , FeedbackActivity.class );
        startActivity( feedbackTv );
    }
}