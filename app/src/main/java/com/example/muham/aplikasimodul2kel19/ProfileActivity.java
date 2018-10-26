package com.example.muham.aplikasimodul2kel19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private User usermodel;
    private EditText etUsernameRegister;
    private EditText etPasswordRegister;
    private Button btEdit, btHapus;
    private String username,password;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();
        edit();
        hapus();

    }

    private void initView() {
        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btEdit = findViewById(R.id.btnEdit);
        btHapus = findViewById(R.id.btnDelete);

        initPreference();
    }

    private void edit() {
        btEdit.setOnClickListener(V ->
                editDataHandler()
        );
    }

    private void hapus() {
        btHapus.setOnClickListener(V ->
                hapusDataHandler()
        );
    }

    private void initPreference() {
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        id = preferences.getInt("id", 0);
        etUsernameRegister.setText(username);
    }

    private void editUser() {
        username = etUsernameRegister.getText().toString();
        password = etPasswordRegister.getText().toString();

        if(password.length() >= 6){
            usermodel = new User();
            usermodel.setId(id);
            usermodel.setUsername(username);
            usermodel.setPassword(password);
        } else {
            Toast.makeText(this, "Password Kurang dari 6 Karakter", Toast.LENGTH_SHORT).show();
            Log.e("Validation","false");
        }
    }

    private void editDataHandler() {
        editUser();

        databaseHandler = new DatabaseHandler(this);
        databaseHandler.updateUser(usermodel);
        User model = databaseHandler.getMahasiswa(1);
        Log.e("record", model.getUsername().toString());
        Intent admin = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(admin);
        CustomIntent.customType(this, "fadein-to-fadeout");
        finish();
    }

    private void hapusDataHandler(){
        deletePreference();
        databaseHandler = new DatabaseHandler(this);
        databaseHandler.deleteUser(id);
        Intent admin = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(admin);
        CustomIntent.customType(this, "fadein-to-fadeout");
        finish();
    }

    private void deletePreference(){
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
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
