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


public class RegisterActivity extends AppCompatActivity {

    private TextView textMasuk;
    private EditText editUserName;
    private EditText editPassWord;
    private EditText editRePassWord;
    private EditText editPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        declareView();
        textMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiRegister();
            }
        });

    }

    private void declareView() {
        textMasuk = findViewById(R.id.text_reg_masuk);
        editUserName = findViewById(R.id.edit_reg_username);
        editPassWord = findViewById(R.id.edit_reg_password);
        editRePassWord = findViewById(R.id.edit_reg_password_confirm);
        editPhoneNumber = findViewById(R.id.edit_reg_phone);
    }

    private void validasiRegister(){

        editUserName.setError(null);
        editPassWord.setError(null);
        editRePassWord.setError(null);
        View fokus = null;
        boolean cancel = false;

        //Set Input Value dari View
        String userName = editUserName.getText().toString();
        String password = editPassWord.getText().toString();
        String rePassword = editRePassWord.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();


        if (TextUtils.isEmpty(userName)){
            editUserName.setError("Harus diisi");
            fokus = editUserName;
            cancel = true;
        }else if(cekUser(userName)){
            editUserName.setError("Username sudah terdaftar");
            fokus = editUserName;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            editPassWord.setError("Harus Diisi");
            fokus = editPassWord;
            cancel = true;
        }else if (!cekPassword(password,rePassword)){
            editPassWord.setError("Password yang dimasukkan tidak sesuai");
            fokus = editPassWord;
            cancel = true;
        }


        if (cancel){
            fokus.requestFocus();
        }else{


            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);
            userModel.setPhone(phoneNumber);
            // Simpan data ke shared preferences
            Preferences.setUserPreferences(getBaseContext(),userModel);
            Preferences.setLoggedInStatus(getBaseContext(),true);
            //Pindah Halaman ke home
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

    }



    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }


    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

}
