package com.example.carterangkotapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carterangkotapps.api.RequestAPI;
import com.example.carterangkotapps.model.JsonResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    private ImageButton calling, whatsapp, sms;
    private TextView phone;

    private static final int REQUEST_CALL = 1;
    public static final String Url = "http://192.168.16.12/driver/listdriver.php";
    String no_polisi, nama, umur, alamat, no_tlp;

    @BindView(R.id.textNo_polisi)
    TextView textNo_polisi;
    @BindView(R.id.textSim)
    TextView textNo_sim;
    @BindView(R.id.textNama)
    TextView textNama;
    @BindView(R.id.textUmur)
    TextView textUmur;
    @BindView(R.id.textAlamat)
    TextView textAlamat;
    @BindView(R.id.textNo_telepon)
    TextView textNo_tlp;
    @BindView(R.id.textTipeMobil)
    TextView textTipe;
    @BindView(R.id.textIdDriver)
    TextView textId_driver;
    @BindView(R.id.textJurusan)
    TextView textJurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ButterKnife.bind( this );
        phone = findViewById( R.id.textNo_telepon );
        whatsapp = findViewById( R.id.whatsapp );
        whatsapp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_driver = textId_driver.getText().toString();
                String nama_driver = textNama.getText().toString();
                String no_sim = textNo_sim.getText().toString();
                String alamat = textAlamat.getText().toString();
                String umur = textUmur.getText().toString();
                String no_tlp = textNo_tlp.getText().toString();
                String tipe_mobil = textTipe.getText().toString();
                String jurusan = textJurusan.getText().toString();
                String plat_mobil = textNo_polisi.getText().toString();

                Intent w = new Intent( v.getContext(), WhatsappActivity.class );
                w.putExtra( "no_tlp",no_tlp );
                v.getContext().startActivity( w );
                /*
                Intent wa = new Intent( UpdateActivity.this, WhatsappActivity.class );
                startActivity( wa );

                 */
            }
        } );
        sms = findViewById( R.id.message );
        sms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_driver = textId_driver.getText().toString();
                String nama_driver = textNama.getText().toString();
                String no_sim = textNo_sim.getText().toString();
                String alamat = textAlamat.getText().toString();
                String umur = textUmur.getText().toString();
                String no_tlp = textNo_tlp.getText().toString();
                String tipe_mobil = textTipe.getText().toString();
                String jurusan = textJurusan.getText().toString();
                String plat_mobil = textNo_polisi.getText().toString();

                Intent i = new Intent( v.getContext(), SMSActivity.class );
                i.putExtra( "no_tlp",no_tlp );
                v.getContext().startActivity( i );

                /*
                Intent intent = new Intent( UpdateActivity.this, SMSActivity.class );
                startActivity( intent );

                openDialog();

                 */
            }
        } );

        calling = findViewById( R.id.call );
        calling.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        } );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Url )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        RequestAPI api = retrofit.create( RequestAPI.class );
        Call<JsonResponse> call = api.update();

        call.enqueue( new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call , Response<JsonResponse> response) {
                String value = response.body().getValue();
            }

            @Override
            public void onFailure(Call<JsonResponse> call , Throwable t) {
            }
        } );

        Intent intent = getIntent();
        String id_driver = intent.getStringExtra( "id_driver" );
        String nama_driver = intent.getStringExtra( "nama_driver" );
        String no_sim = intent.getStringExtra( "no_sim" );
        String umur = intent.getStringExtra( "umur" );
        String alamat = intent.getStringExtra( "alamat" );
        String no_tlp = intent.getStringExtra( "no_tlp" );
        String tipe_mobil = intent.getStringExtra( "tipe_mobil" );
        String plat_mobil = intent.getStringExtra( "plat_mobil" );
        String jurusan_mobil = intent.getStringExtra( "jurusan_mobil" );

        textId_driver.setText( id_driver );
        textNama.setText( nama_driver );
        textNo_sim.setText( no_sim );
        textUmur.setText( umur );
        textAlamat.setText( alamat );
        textNo_tlp.setText( no_tlp );
        textTipe.setText( tipe_mobil );
        textNo_polisi.setText(plat_mobil );
        textJurusan.setText( jurusan_mobil );
    }
/*
    private void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show( getSupportFragmentManager(),"Form Pesanan" );
    }

    @Override
    public void applyText(String name , String address , String hp) {

    }

 */

    private void makePhoneCall() {
        String no_tlp = textNo_tlp.getText().toString();

        if (Build.VERSION.SDK_INT > 22) {
            if (ActivityCompat.checkSelfPermission( UpdateActivity.this , Manifest.permission.CALL_PHONE ) != PackageManager
                    .PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions( UpdateActivity.this , new String[]{Manifest.permission.CALL_PHONE} , REQUEST_CALL );

                return;
            }

            Intent callintent = new Intent( Intent.ACTION_CALL );
            callintent.setData( Uri.parse( "tel:" + textNo_tlp.getText().toString().trim() ) );
            startActivity( callintent );

        }else {
            Intent callintent = new Intent( Intent.ACTION_CALL );
            callintent.setData( Uri.parse( "tel:" + textNo_tlp.getText().toString().trim() ) );
            startActivity( callintent );
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void back(View view) {
        Intent back = new Intent( UpdateActivity.this, DashboardActivity.class );
        startActivity( back );
    }
    public void onBackPressed() {
        Intent startMain = new Intent(UpdateActivity.this, DashboardActivity.class);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}