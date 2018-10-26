package com.example.muham.aplikasimodul2kel19;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private User usermodel;
    private EditText etUsernameRegister;
    private EditText etPasswordRegister;
    private Button btRegister;
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        register();
    }

    private void register() {
        btRegister.setOnClickListener(V ->
                validation()
        );
    }

    private void initView() {
        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btRegister = findViewById(R.id.btRegister);
    }

    private void initUser() {
        username = etUsernameRegister.getText().toString();
        password = etPasswordRegister.getText().toString();
        usermodel = new User();
        usermodel.setUsername(username);
        usermodel.setPassword(password);
    }

    private void validation(){
        password = etPasswordRegister.getText().toString();
        if(password.length() >=6){
            initDataHandler();
        } else {
            Toast.makeText(this, "Password Kurang dari 6 Karakter", Toast.LENGTH_SHORT).show();
            Log.e("Validation","false");
        }
    }

    private void initDataHandler() {
        initUser();

        databaseHandler = new DatabaseHandler(this);
        databaseHandler.addUser(usermodel);
        User model = databaseHandler.getMahasiswa(1);
        Log.e("record", model.getUsername().toString());
        Intent admin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(admin);
        CustomIntent.customType(this, "fadein-to-fadeout");
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        Intent reg = new Intent(this, MainActivity.class);
        startActivity(reg);
        finish();
    }
}
