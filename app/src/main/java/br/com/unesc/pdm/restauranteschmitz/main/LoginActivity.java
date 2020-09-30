package br.com.unesc.pdm.restauranteschmitz.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.unesc.pdm.restauranteschmitz.R;
import br.com.unesc.pdm.restauranteschmitz.lib.LoggerPreferences;

public class LoginActivity extends AppCompatActivity {

    private EditText txt_user, txt_password;
    private Button btn_log_in;
    private LoggerPreferences loggerPreferences;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_user = findViewById(R.id.txt_user);
        txt_password = findViewById(R.id.txt_password);
        btn_log_in = findViewById(R.id.btn_log_in);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()) {
                    if(auth()) {
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        it.putExtra("user", user);
                        startActivity(it);
                    }
                }
            }
        });
    }

    private boolean validateFields() {

        if(txt_user.getText().toString().isEmpty() || txt_user.getText().toString().contains(" ")) {
            txt_user.setError("Usuário não pode conter espaços ou ficar em branco!");
            return false;
        } else if(txt_password.getText().toString().isEmpty() || txt_password.getText().toString().contains(" ")) {
            txt_password.setError("Senha não pode conter espaços ou ficar em branco!");
            return false;
        }

        return true;
    }

    private boolean auth() {
        String user = txt_user.getText().toString(), password = txt_password.getText().toString();

        switch (user) {
            case "Administrador":
                if (password.equals("Administrador")) {
                    this.user = "Administrador";
                    return true;
                }
                txt_password.setError("Senha Inválida!");
                return false;
            case "Adm":
                if (password.equals("Adm123")) {
                    this.user = "Adm";
                    return true;
                }
                txt_password.setError("Senha Inválida!");
                return false;
            case "Administrator":
                if (password.equals("Que3B1eng4ElT0r0")) {
                    this.user = "Administrator";
                    return true;
                }
                txt_password.setError("Senha Inválida!");
                return false;
            case "Root":
                if (password.equals("pr0m1uscu0")) {
                    this.user = "Root";
                    return true;
                }
                txt_password.setError("Senha Inválida!");
                return false;
        }

        txt_user.setError("Usuário Inválido!");
        return false;
    }
}
