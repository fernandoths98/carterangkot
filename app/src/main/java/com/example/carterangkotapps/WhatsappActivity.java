package com.example.carterangkotapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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

public class WhatsappActivity extends AppCompatActivity {

    EditText psnpesan;
    ImageButton btnwa, btnhomewa;

    public static final String Url = "http://192.168.16.12/driver/listdriver.php";
    String no_polisi, nama, umur, alamat, no_tlp;


    @BindView(R.id.no_hp_wa)
    TextView textNo_tlp_wa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        ButterKnife.bind( this );

        Intent intent = getIntent();

        String no_tlp = intent.getStringExtra( "no_tlp" );

        textNo_tlp_wa.setText( no_tlp );

        btnhomewa = findViewById( R.id.back );
        btnhomewa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent( WhatsappActivity.this , HomeActivity.class );
                startActivity( back );
            }
        } );
        psnpesan = findViewById( R.id.pesan_wa );
        btnwa = findViewById( R.id.send_wa );
        btnwa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pesan_wa = psnpesan.getText().toString();
                String no_tlp_wa = textNo_tlp_wa.getText().toString();
                boolean installed = appInstalledOrNot( "com.whatsapp" );

                if (installed) {
                    Intent intent = new Intent( Intent.ACTION_VIEW );
                    intent.setData( Uri.parse( "http://api.whatsapp.com/send?phone=" + "+62" + no_tlp_wa + "&text=" + pesan_wa ) );
                    startActivity( intent );
                } else {
                    Toast.makeText( WhatsappActivity.this , "Aplikasi Whatsapp Tidak Terinstal Pada Smartphone Anda" , Toast.LENGTH_SHORT ).show();
                }
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



    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo( url , PackageManager.GET_ACTIVITIES );
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }

        return app_installed;
    }

    public void onBackPressed() {
        Intent startMain = new Intent(WhatsappActivity.this, DashboardActivity.class);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}