package com.example.prm392miniproject_carracebet;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main_screen extends AppCompatActivity {

    //Buttons
    private Button btnStart;
    private Button btnReset;

    //Seekbars
    private SeekBar sbCar1;
    private SeekBar sbCar2;
    private SeekBar sbCar3;
    private SeekBar sbCar4;

    //Checkboxes for betting
    private CheckBox cbCar1;
    private CheckBox cbCar2;
    private CheckBox cbCar3;
    private CheckBox cbCar4;

    //Add money button
    private View btnAddCurrency;

    private TextView txtCurrency;



    private double money;
    private TextView tvMoney;

    Dialog addAmountDialog;
    Button btnAddAmountCancel, btnAddAmount;
    EditText edtAmountToAdd;
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
        txtCurrency = findViewById(R.id.Account);

        //Get fields data
        btnStart = findViewById(R.id.startButton);
        btnReset = findViewById(R.id.resetButton);
        btnAddCurrency = findViewById(R.id.addCurrency);

        sbCar1 = findViewById(R.id.firstCar);
        sbCar2 = findViewById(R.id.secondCar);
        sbCar3 = findViewById(R.id.thirdCar);
        sbCar4 = findViewById(R.id.fourthCar);

        cbCar1 = findViewById(R.id.betCar1);
        cbCar2 = findViewById(R.id.betCar2);
        cbCar3 = findViewById(R.id.betCar3);
        cbCar4 = findViewById(R.id.betCar4);

        addAmountDialog = new Dialog(main_screen.this);
        addAmountDialog.setContentView(R.layout.dialog_addcash);
        addAmountDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addAmountDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_dialog_bg));
        addAmountDialog.setCancelable(false);

        btnAddAmountCancel = (Button) addAmountDialog.findViewById(R.id.btnCancelAddAmount);
        btnAddAmount = (Button) addAmountDialog.findViewById(R.id.btnAddAmount);

        btnAddAmountCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmountDialog.dismiss();
            }
        });

        btnAddAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAmountToAdd = (EditText) addAmountDialog.findViewById(R.id.edtAmmount);
                if (TextUtils.isEmpty(edtAmountToAdd.getText())) {
                    edtAmountToAdd.setError("Required");
                } else {
                    tvMoney.setText(edtAmountToAdd.getText());
                    addAmountDialog.dismiss();
                    Toast.makeText(main_screen.this, "Money added!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //OnClick Functions
        btnStart.setOnClickListener(new View.OnClickListener() {
            //Confirm before start
            //Hide all the UIs when the race is started
            @Override
            public void onClick(View v) {
                Toast.makeText(main_screen.this, "The race is started !", Toast.LENGTH_SHORT).show();



            hideUI();
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(main_screen.this, "Reset the game !", Toast.LENGTH_SHORT).show();

            }
        });

        btnAddCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmountDialog.show();
            }
        });

        //Hiện popup nhập số tiền bet khi tick chọn vào 1 xe
        cbCar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Test
                Toast.makeText(main_screen.this, "Bet for car 1 is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        cbCar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(main_screen.this, "Bet for car 2 is clicked", Toast.LENGTH_SHORT).show();

            }
        });

        cbCar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(main_screen.this, "Bet for car 3 is clicked", Toast.LENGTH_SHORT).show();

            }
        });

        cbCar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(main_screen.this, "Bet for car 4 is clicked", Toast.LENGTH_SHORT).show();

            }
        });




    }

    public void hideUI(){
        //Hide buttons
        btnStart.setVisibility(View.INVISIBLE);
        btnReset.setVisibility(View.INVISIBLE);
        btnAddCurrency.setVisibility(View.INVISIBLE);
        //Hide checkboxes
        cbCar1.setVisibility(View.INVISIBLE);
        cbCar2.setVisibility(View.INVISIBLE);
        cbCar3.setVisibility(View.INVISIBLE);
        cbCar4.setVisibility(View.INVISIBLE);
        //Hide texts
        txtCurrency.setVisibility(View.INVISIBLE);
        tvMoney.setVisibility(View.INVISIBLE);

    }



}