package com.example.prm392miniproject_carracebet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login_screen extends AppCompatActivity {

    private EditText edEmail;
    private EditText edPassword;
    private Button btnLogin;
    private static final String VALID_EMAIL = "user@example.com";
    private static final String VALID_PASSWORD = "password123";
    public static final String EXTRA_MONEY = "com.example.myapp.MONEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login_screen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            String email = edEmail.getText().toString();
            String password = edPassword.getText().toString();

            if (email.equals(VALID_EMAIL) && password.equals(VALID_PASSWORD)) {
                Intent intent = new Intent(login_screen.this, main_screen.class);
                intent.putExtra(EXTRA_MONEY, 100);
                startActivity(intent);
            } else {
                Toast.makeText(login_screen.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}