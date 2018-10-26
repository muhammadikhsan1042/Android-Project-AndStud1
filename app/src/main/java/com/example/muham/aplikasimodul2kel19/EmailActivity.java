package com.example.muham.aplikasimodul2kel19;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EmailActivity extends AppCompatActivity {

    Button buttonSend;
    EditText textTo, textSbj, textMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textTo = (EditText) findViewById(R.id.editTextTo);
        textSbj = (EditText) findViewById(R.id.editTextSbj);
        textMsg = (EditText) findViewById(R.id.editTextMsg);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = textTo.getText().toString();
                String subject = textSbj.getText().toString();
                String message = textMsg.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                email.setType("massage/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client"));
            }
        });
    }
    @Override
    public void onBackPressed(){back();}

    private void back(){
        Intent req = new Intent(this, MainActivity.class);
        startActivity(req);
        finish();
    }
}
