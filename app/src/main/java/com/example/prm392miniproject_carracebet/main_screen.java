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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

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

    final List<Integer> duration = Collections.synchronizedList(new ArrayList<>());

    private double money;
    private TextView tvMoney;
    private Random random = new Random();
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
                CountDownLatch latch = new CountDownLatch(4); // 4 for four cars
                int max = 500;
                // Generate random distances for each car
                new Thread(() -> {
                    int car1Speed = 1;
                    int car1Distance = 0;
                    while (car1Distance < max) {
                        car1Distance += random.nextInt(Math.min(car1Speed + 2, max));
                        car1Speed = car1Distance;
                        try {
                            Thread.sleep(500); // Delay 100ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final int finalCar1Distance = car1Distance;
                        runOnUiThread(() -> sbCar1.setProgress(finalCar1Distance));
                    }
                    latch.countDown();
                    duration.add(1);
                }).start();

                new Thread(() -> {
                    int car2Speed = 1;
                    int car2Distance = 0;
                    while (car2Distance < max) {
                        car2Distance += random.nextInt(Math.min(car2Speed + 2, max));
                        car2Speed = car2Distance;
                        try {
                            Thread.sleep(500); // Delay 100ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final int finalCar2Distance = car2Distance;
                        runOnUiThread(() -> sbCar2.setProgress(finalCar2Distance));
                    }
                    latch.countDown();
                    duration.add(2);

                }).start();

                new Thread(() -> {
                    int car3Speed = 1;
                    int car3Distance = 0;
                    while (car3Distance < max) {
                        car3Distance += random.nextInt(Math.min(car3Speed + 2, max));
                        car3Speed = car3Distance;
                        try {
                            Thread.sleep(500); // Delay 100ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final int finalCar3Distance = car3Distance;
                        runOnUiThread(() -> sbCar3.setProgress(finalCar3Distance));
                    }
                    latch.countDown();
                    duration.add(3);

                }).start();

                new Thread(() -> {
                    int car4Speed = 1;
                    int car4Distance = 0;
                    while (car4Distance < max) {
                        car4Distance += random.nextInt(Math.min(car4Speed + 2, max));
                        car4Speed = car4Distance;
                        try {
                            Thread.sleep(500); // Delay 100ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final int finalCar4Distance = car4Distance;
                        runOnUiThread(() -> sbCar4.setProgress(finalCar4Distance));
                    }
                    latch.countDown();
                    duration.add(4);

                }).start();

                new Thread(() -> {
                    try {
                        latch.await();
                        runOnUiThread(() -> {
                            Toast.makeText(main_screen.this, "Car " + duration.get(0)+" is the winner!", Toast.LENGTH_SHORT).show();
                            ShowUI();
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();



            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(main_screen.this, "Reset the game !", Toast.LENGTH_SHORT).show();
                ResetProgress();
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
    public void ShowUI(){
        //Hide buttons
        btnStart.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.VISIBLE);
        btnAddCurrency.setVisibility(View.VISIBLE);
        //Hide checkboxes
        cbCar1.setVisibility(View.VISIBLE);
        cbCar2.setVisibility(View.VISIBLE);
        cbCar3.setVisibility(View.VISIBLE);
        cbCar4.setVisibility(View.VISIBLE);
        //Hide texts
        txtCurrency.setVisibility(View.VISIBLE);
        tvMoney.setVisibility(View.VISIBLE);

    }

    public void ResetProgress(){
        sbCar1.setProgress(0);
        sbCar2.setProgress(0);
        sbCar3.setProgress(0);
        sbCar4.setProgress(0);

        cbCar1.setChecked(false);
        cbCar2.setChecked(false);
        cbCar3.setChecked(false);
        cbCar4.setChecked(false);
    }


}