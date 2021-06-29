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

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnlogin;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.16.12/driver/listdriver.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById( R.id.email_login );
        password = findViewById( R.id.password_login );
        btnlogin = findViewById( R.id.login );

        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_mail = email.getText().toString();
                String user_password = password.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl( "http://192.168.16.12/driver/listdriver.php" )
                        .addConverterFactory( GsonConverterFactory.create() )
                        .build();

                RequestAPI request = retrofit.create( RequestAPI.class );
                Call<JsonResponse> call = request.login( user_mail, user_password );
                call.enqueue( new Callback<JsonResponse>() {
                    @Override
                    public void onResponse(Call<JsonResponse> call , Response<JsonResponse> response) {
                        if (response.code()==200){
                            JsonResponse jsonResponse = response.body();
                            Intent home = new Intent( LoginActivity.this, DashboardActivity.class );
                            Toast.makeText( getApplicationContext() , jsonResponse.getResponse().toString() , Toast.LENGTH_SHORT ).show();
                            startActivity( home );
                        }
                        else {
                            Toast.makeText( getApplicationContext() , String.valueOf( response.code() ) , Toast.LENGTH_SHORT ).show();
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


    public void create(View view) {
        Intent create = new Intent( LoginActivity.this, RegisterActivity.class );
        startActivity( create );
    }

    public void google(View view) {
        Intent google = new Intent( LoginActivity.this, GoogleActivity.class );
        startActivity( google );
    }

}