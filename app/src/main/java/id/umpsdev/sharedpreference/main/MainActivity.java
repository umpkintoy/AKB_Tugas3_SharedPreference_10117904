package id.umpsdev.sharedpreference.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import id.umpsdev.sharedpreference.R;
import id.umpsdev.sharedpreference.util.Preferences;

/*
Nama    : Siti Safira Nadifa
NIM     : 10117904
Kelas   : IF-6K
Tanggal Pengerjaan : 30-04-2020
 */
public class MainActivity extends AppCompatActivity {

    private TextView textKeluar;
    private TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        declareView();
        textKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hapus Set Preferences
                Preferences.setLogout(getBaseContext());

                //Pindah ke Halaman Login
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });

    }

    private void declareView() {
        textKeluar = findViewById(R.id.text_logout);
        textName = findViewById(R.id.textName);

        textName.setText(id.umpsdev.sharedpreference.util.Preferences.getRegisteredUser(getBaseContext()));
    }
}

