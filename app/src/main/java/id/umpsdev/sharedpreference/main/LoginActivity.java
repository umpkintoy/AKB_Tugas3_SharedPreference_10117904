package id.umpsdev.sharedpreference.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.umpsdev.sharedpreference.R;
import id.umpsdev.sharedpreference.model.UserModel;
import id.umpsdev.sharedpreference.util.Preferences;

/*
Nama    : Siti Safira Nadifa
NIM     : 10117904
Kelas   : IF-6K
Tanggal Pengerjaan : 30-04-2020
 */

public class LoginActivity extends AppCompatActivity {

    private TextView textMasuk;
    private TextView textRegister;
    private EditText editUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        declareView();

        textMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }


    private void declareView() {

        textRegister = findViewById(R.id.text_login_register);
        textMasuk = findViewById(R.id.text_login_masuk);
        editUsername = findViewById(R.id.edit_login_username);
        editPassword = findViewById(R.id.edit_login_password);

    }

    private void validasiLogin() {

        // Mereset semua Error dan fokus menjadi default
        editUsername.setError(null);
        editPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        //Set Input Value dari View
        String userName = editUsername.getText().toString();
        String password = editPassword.getText().toString();


        if (TextUtils.isEmpty(userName)) {
            editUsername.setError("Harus diisi");
            fokus = editUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            editUsername.setError("Username Tidak Ditemukan");
            fokus = editUsername;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)) {
            editPassword.setError("Harus Diisi");
            fokus = editPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            editPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = editPassword;
            cancel = true;
        }


        if (cancel) {
            fokus.requestFocus();
        } else {
            // Deklarasi Model
            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);

            // Simpan data ke shared preferences
            id.umpsdev.sharedpreference.util.Preferences.setUserPreferences(getBaseContext(), userModel);
            id.umpsdev.sharedpreference.util.Preferences.setLoggedInStatus(getBaseContext(), true);

            //Pindah ke halaman home
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

    }


    private boolean cekUser(String user) {
        return user.equals(id.umpsdev.sharedpreference.util.Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password) {
        return password.equals(id.umpsdev.sharedpreference.util.Preferences.getRegisteredPassword(getBaseContext()));
    }


}

