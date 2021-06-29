package com.example.carterangkotapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carterangkotapps.api.RequestAPI;
import com.example.carterangkotapps.model.JsonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText nama, email, password, c_password;
    private Button btnregis;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.16.12/driver/listdriver.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById( R.id.loading );
        nama = findViewById( R.id.nama );
        email = findViewById( R.id.email );
        password = findViewById( R.id.password );
        c_password = findViewById( R.id.c_password );

        btnregis = findViewById( R.id.regis );
        btnregis.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_mail = email.getText().toString();
                String user_name = nama.getText().toString();
                String user_password = password.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl( "http://192.168.16.12/driver/listdriver.php" )
                        .addConverterFactory( GsonConverterFactory.create() )
                        .build();

                RequestAPI request = retrofit.create( RequestAPI.class );
                Call<JsonResponse> call = request.create( user_name, user_password, user_mail );
                call.enqueue( new Callback<JsonResponse>() {
                    @Override
                    public void onResponse(Call<JsonResponse> call , Response<JsonResponse> response) {
                        if (response.code()==200){
                            JsonResponse response1 =response.body();
                            Intent home = new Intent( RegisterActivity.this, DashboardActivity.class);
                            Toast.makeText( getApplicationContext() , response1.getResponse().toString() , Toast.LENGTH_SHORT ).show();
                            startActivity( home );
                        } else {
                            Toast.makeText( getApplicationContext() , String.valueOf( response.body() ) , Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonResponse> call , Throwable t) {
                        Toast.makeText( getApplicationContext() , "Error!!!" , Toast.LENGTH_SHORT ).show();
                    }
                } );
            }
        } );
    }

    public void login(View view) {
        Intent login = new Intent( RegisterActivity.this, LoginActivity.class );
        startActivity( login );
    }
}