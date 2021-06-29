package com.example.carterangkotapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
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

public class SMSActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_SEND_SMS = 0;
    EditText psnnomor;
    ImageButton btnsms, btnhomesms;

    public static final String Url = "http://192.168.16.12/driver/listdriver.php";
    String no_polisi, nama, umur, alamat, no_tlp;


    @BindView(R.id.no_hp_sms)
    TextView textNo_tlp_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s);
        ButterKnife.bind( this );

        if (ContextCompat.checkSelfPermission( this, Manifest.permission.SEND_SMS ) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.SEND_SMS ))
            {

            } else
            {
                ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSION_REQUEST_SEND_SMS );
            }
        }


        btnhomesms = findViewById( R.id.back );
        btnhomesms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent( SMSActivity.this, HomeActivity.class );
                startActivity( back );
            }
        } );
        psnnomor = findViewById( R.id.pesan );
        btnsms = findViewById( R.id.send_sms );
        btnsms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
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
        String no_tlp = intent.getStringExtra( "no_tlp" );

        textNo_tlp_sms.setText( no_tlp );
    }

    private void sendSMS() {
        String msg = psnnomor.getText().toString().trim();
        String no_hp_sms = textNo_tlp_sms.getText().toString().trim();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage( no_hp_sms, null, msg, null, null );
        Toast.makeText( this , "Pesan Terkirim" , Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String permissions[], int[] grandResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSION_REQUEST_SEND_SMS:
            {
                if (grandResults.length>0 && grandResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText( this , "Makasih Udah Kasih Izin!!" , Toast.LENGTH_SHORT ).show();
                }
                else
                {
                    Toast.makeText( this , "Kok Pelit Amat Sih Gak Dikasih Izin!!" , Toast.LENGTH_SHORT ).show();
                }
            }
        }
    }

    public void onBackPressed() {
        Intent startMain = new Intent(SMSActivity.this, DashboardActivity.class);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}