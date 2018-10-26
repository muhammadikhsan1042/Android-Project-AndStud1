package com.example.muham.aplikasimodul2kel19;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private User usermodel;
    private TextView tvNameMain;
    private Button btnLogoutMain;
    private Button exit;
    private Button btnEmail;
    private Button btnProfile;
    private Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPreference();
        logout();
        profile();
        email();
        about();
    }

    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    private void initView() {
        tvNameMain = findViewById(R.id.tvNameMain);
        btnLogoutMain = findViewById(R.id.btnLogoutMain);
        exit = findViewById(R.id.btnLogoutMain);
        btnEmail = findViewById(R.id.btnEmail);
        btnProfile = findViewById(R.id.btnProfile);
        btnAbout = findViewById(R.id.btnAbout);
    }

    private void profile() {
        btnProfile.setOnClickListener(v -> {
            Intent reg = new Intent(this, ProfileActivity.class);
            startActivity(reg);
            CustomIntent.customType(this, "fadein-to-fadeout");
            finish();
        });
    }

    private void about() {
        btnAbout.setOnClickListener(v -> {
            Intent reg = new Intent(this, AboutActivity.class);
            startActivity(reg);
            CustomIntent.customType(this, "fadein-to-fadeout");
            finish();
        });
    }

    private void email() {
        btnEmail.setOnClickListener(v -> {
            Intent reg = new Intent(this, EmailActivity.class);
            startActivity(reg);
            CustomIntent.customType(this, "fadein-to-fadeout");
            finish();
        });
    }

    private void initPreference() {
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        tvNameMain.setText(username);
    }

    private void deletePreference(){
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }

    private void logout() {
        exit.setOnClickListener(view -> showAlertDialog());
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePreference();
                        Intent login = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
