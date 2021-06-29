package com.example.carterangkotapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText edit1 = (EditText) findViewById( R.id.edittext_name );
        final EditText edit2 = (EditText) findViewById( R.id.edittext_feedback );
        Button btn = (Button)findViewById( R.id.buttonFeedback );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] to_emails = {"campdevapps@gmail.com"};
                Intent i = new Intent( Intent.ACTION_SEND );
                i.setType( "message/html" );
                i.putExtra( Intent.EXTRA_EMAIL, to_emails );
                i.putExtra( Intent.EXTRA_SUBJECT, " FeedBack From App " );
                i.putExtra( Intent.EXTRA_TEXT, " Name : "+edit1.getText()+"\n Message : "+edit2.getText() );
                try {
                    startActivity( Intent.createChooser( i, "Please select Email" ) );
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText( FeedbackActivity.this , "There are no Email Clients" , Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }

    public void onBackPressed() {
        Intent startMain = new Intent(FeedbackActivity.this, DashboardActivity.class);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }
}