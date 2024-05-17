package com.example.prm392miniproject_carracebet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main_screen extends AppCompatActivity {
    private double money;
    private TextView tvMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_screen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Tao quăng money qua nha mấy cu
        // Có gì làm layout tự chỉnh
        Intent intent = getIntent();
        money = intent.getIntExtra(login_screen.EXTRA_MONEY, 0);

        tvMoney = findViewById(R.id.tvMoney);
        tvMoney.setText(String.valueOf(money));
    }
}