package com.example.carterangkotapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class GoogleActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 7117;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);

            providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(), //Email Login
                    new AuthUI.IdpConfig.GoogleBuilder().build() //Google Login

            );

            showSignInOption();
        }

        private void showSignInOption () {
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.angkot)
                            .setTheme(R.style.MyTheme)
                            .build(), MY_REQUEST_CODE
            );
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == MY_REQUEST_CODE) {
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (resultCode == RESULT_OK) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                    android.content.Intent start = new Intent(GoogleActivity.this, DashboardActivity.class);
                    startActivity(start);
                    finish();
                } else {
                    Intent back = new Intent(GoogleActivity.this, LoginActivity.class);
                    startActivity(back);
                }
            }
        }
    }
