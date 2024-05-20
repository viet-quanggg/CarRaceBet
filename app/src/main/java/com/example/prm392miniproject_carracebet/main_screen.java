package com.example.prm392miniproject_carracebet;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
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
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class main_screen extends AppCompatActivity {

    final List<Integer> duration = Collections.synchronizedList(new ArrayList<>());
    private List<TextView> textViews = Collections.synchronizedList(new ArrayList<>());
    Dialog addAmountDialog;
    Dialog addBetDialog;
    Dialog addBetDialog2;
    Dialog addBetDialog3;
    Dialog addBetDialog4;
    Button btnAddAmountCancel, btnAddAmount;
    EditText edtAmountToAdd;
    EditText edtBetAmountToAdd;
    Button btnAddBetAmountCancel, btnAddBetAmount, btnAddBetAmountCancel2, btnAddBetAmount2, btnAddBetAmountCancel3, btnAddBetAmount3, btnAddBetAmountCancel4, btnAddBetAmount4;
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
    private TextView txtCar1Bet;
    private TextView txtCar2Bet;
    private TextView txtCar3Bet;
    private TextView txtCar4Bet;
    private int money;
    private TextView tvMoney;
    private final Random random = new Random();
    private MediaPlayer bg_music;
    private MediaPlayer btn_ClickSound;
    private MediaPlayer checked_Sound;
    private MediaPlayer car_race_sound;
    private MediaPlayer winner_sound;
    private MediaPlayer notification_sound;

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
        Intent intent = getIntent();
        money = intent.getIntExtra("EXTRA_MONEY", 0);

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

        txtCar1Bet = findViewById(R.id.Car1Bet);
        txtCar2Bet = findViewById(R.id.Car2Bet);
        txtCar3Bet = findViewById(R.id.Car3Bet);
        txtCar4Bet = findViewById(R.id.Car4Bet);


        addAmountDialog = new Dialog(main_screen.this);
        addAmountDialog.setContentView(R.layout.dialog_addcash);
        addAmountDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addAmountDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_dialog_bg));
        addAmountDialog.setCancelable(false);

        addBetDialog = new Dialog(main_screen.this);
        addBetDialog.setContentView(R.layout.add_bet_dialog);
        addBetDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addBetDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_dialog_bg));
        addBetDialog.setCancelable(false);

        addBetDialog2 = new Dialog(main_screen.this);
        addBetDialog2.setContentView(R.layout.add_bet_dialog);
        addBetDialog2.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addBetDialog2.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_dialog_bg));
        addBetDialog2.setCancelable(false);

        addBetDialog3 = new Dialog(main_screen.this);
        addBetDialog3.setContentView(R.layout.add_bet_dialog);
        addBetDialog3.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addBetDialog3.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_dialog_bg));
        addBetDialog3.setCancelable(false);

        addBetDialog4 = new Dialog(main_screen.this);
        addBetDialog4.setContentView(R.layout.add_bet_dialog);
        addBetDialog4.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addBetDialog4.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_dialog_bg));
        addBetDialog4.setCancelable(false);


        btnAddAmountCancel = addAmountDialog.findViewById(R.id.btnCancelAddAmount);
        btnAddAmount = addAmountDialog.findViewById(R.id.btnAddAmount);

        btnAddBetAmountCancel = addBetDialog.findViewById(R.id.btnCancelBetAmount);
        btnAddBetAmount = addBetDialog.findViewById(R.id.btnAddBetAmount);

        btnAddBetAmountCancel2 = addBetDialog2.findViewById(R.id.btnCancelBetAmount);
        btnAddBetAmount2 = addBetDialog2.findViewById(R.id.btnAddBetAmount);

        btnAddBetAmountCancel3 = addBetDialog3.findViewById(R.id.btnCancelBetAmount);
        btnAddBetAmount3 = addBetDialog3.findViewById(R.id.btnAddBetAmount);

        btnAddBetAmountCancel4 = addBetDialog4.findViewById(R.id.btnCancelBetAmount);
        btnAddBetAmount4 = addBetDialog4.findViewById(R.id.btnAddBetAmount);


        // Khởi tạo âm thanh nền
        bg_music = MediaPlayer.create(this, R.raw.background_sound);
        bg_music.setLooping(true);
        bg_music.setVolume(0.8f, 0.8f);
        bg_music.start();
        // Khởi tạo âm thanh khi click
        btn_ClickSound = MediaPlayer.create(this, R.raw.button_click_sound);
        // Khởi tạo âm thanh khi bắt đầu đua xe
        car_race_sound = MediaPlayer.create(this, R.raw.car_race_sound);
        // Khởi tạo âm thanh khi chọn xe
        checked_Sound = MediaPlayer.create(this, R.raw.pick_sound);
        // Khởi tạo âm thanh khi có người thắng
        winner_sound = MediaPlayer.create(this, R.raw.winner_sound);
        // Khởi tạo âm thanh khi có thông báo
        notification_sound = MediaPlayer.create(this, R.raw.system_noti_sound);

        textViews.add(txtCar1Bet);
        textViews.add(txtCar2Bet);
        textViews.add(txtCar3Bet);
        textViews.add(txtCar4Bet);

        btnAddAmountCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmountDialog.dismiss();
            }
        });

        btnAddAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAmountToAdd = addAmountDialog.findViewById(R.id.edtAmmount);
                if (TextUtils.isEmpty(edtAmountToAdd.getText())) {
                    edtAmountToAdd.setError("Required");
                } else {
                    tvMoney.setText(edtAmountToAdd.getText());
                    addAmountDialog.dismiss();
                    Toast.makeText(main_screen.this, "Money added!", Toast.LENGTH_SHORT).show();
                    notification_sound.start();
                }
            }
        });
        //OnClick Functions
        btnStart.setOnClickListener(new View.OnClickListener() {
            //Confirm before start
            //Hide all the UIs when the race is started
            @Override
            public void onClick(View v) {
                btn_ClickSound.start();
                bg_music.setVolume(0.4f, 0.4f);
                car_race_sound.start();
                duration.clear();
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
                    duration.add(1);
                    latch.countDown();
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
                    duration.add(2);
                    latch.countDown();
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
                    duration.add(3);
                    latch.countDown();
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
                    duration.add(4);
                    latch.countDown();
                }).start();

                new Thread(() -> {
                    try {
                        latch.await();
                        runOnUiThread(() -> {
                            Toast.makeText(main_screen.this, "Car " + duration.get(0) + " is the winner!", Toast.LENGTH_SHORT).show();
                            ShowUI();

                            for (int i = 1; i <= duration.size()  ; i++){
                                if (i == duration.get(0)){
                                    TextView t = textViews.get(i-1);
                                    String money = t.getText().toString();
                                    String oldmoney = tvMoney.getText().toString();
                                    if (money.isEmpty()){
                                        money = "0";
                                    }
                                    int newmoney = Integer.parseInt(money) + Integer.parseInt(oldmoney);
                                    tvMoney.setText(String.valueOf(newmoney));
                                }
                            }
                            ResetNghi();
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
                btn_ClickSound.start();
                Toast.makeText(main_screen.this, "Reset the game !", Toast.LENGTH_SHORT).show();
                ResetProgress();
            }
        });

        btnAddCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAmountDialog.show();
                btn_ClickSound.start();
            }
        });

        //Hiện popup nhập số tiền bet khi tick chọn vào 1 xe
        cbCar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBetDialog.show();
                checked_Sound.start();
            }
        });

        btnAddBetAmountCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMoney.setText(String.valueOf(refundBet(tvMoney, txtCar1Bet)));
                addBetDialog.dismiss();
                cbCar1.setChecked(false);
                txtCar1Bet.setVisibility(View.INVISIBLE);
            }
        });
        btnAddBetAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtBetAmountToAdd = addBetDialog.findViewById(R.id.edtBetAmount);
                int result = getBetAmount(edtBetAmountToAdd, tvMoney, txtCar1Bet);
                if (result != 0) {
                    int curMoney = Integer.parseInt(tvMoney.getText().toString());
                    int afterBet = curMoney - result;
                    tvMoney.setText(String.valueOf(afterBet));
                    addBetDialog.dismiss();
                    txtCar1Bet.setVisibility(View.VISIBLE);
                    cbCar1.setChecked(true);
                    checked_Sound.start();
                } else {
                    Toast.makeText(main_screen.this, "Error in betting !", Toast.LENGTH_SHORT).show();
                    notification_sound.start();
                }
            }
        });

        cbCar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBetDialog2.show();
                checked_Sound.start();
            }
        });

        btnAddBetAmountCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMoney.setText(String.valueOf(refundBet(tvMoney, txtCar2Bet)));
                addBetDialog2.dismiss();
                cbCar2.setChecked(false);
                txtCar2Bet.setVisibility(View.INVISIBLE);
            }
        });

        btnAddBetAmount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtBetAmountToAdd = addBetDialog2.findViewById(R.id.edtBetAmount);
                int result = getBetAmount(edtBetAmountToAdd, tvMoney, txtCar2Bet);
                if (result != 0) {
                    tvMoney.setText(String.valueOf(updateCurMoney(tvMoney, result)));
                    addBetDialog2.dismiss();
                    txtCar2Bet.setVisibility(View.VISIBLE);
                    cbCar2.setChecked(true);
                    checked_Sound.start();
                } else {
                    Toast.makeText(main_screen.this, "Error in betting !", Toast.LENGTH_SHORT).show();
                    notification_sound.start();
                }
            }
        });


        cbCar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addBetDialog3.show();
                checked_Sound.start();
            }
        });

        btnAddBetAmountCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMoney.setText(String.valueOf(refundBet(tvMoney, txtCar3Bet)));
                addBetDialog3.dismiss();
                cbCar3.setChecked(false);
                txtCar3Bet.setVisibility(View.INVISIBLE);

            }
        });

        btnAddBetAmount3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtBetAmountToAdd = addBetDialog3.findViewById(R.id.edtBetAmount);
                int result = getBetAmount(edtBetAmountToAdd, tvMoney, txtCar3Bet);
                if (result != 0) {
                    tvMoney.setText(String.valueOf(updateCurMoney(tvMoney, result)));
                    addBetDialog3.dismiss();
                    txtCar3Bet.setVisibility(View.VISIBLE);
                    cbCar3.setChecked(true);
                    checked_Sound.start();
                } else {
                    Toast.makeText(main_screen.this, "Error in betting !", Toast.LENGTH_SHORT).show();
                    notification_sound.start();
                }
            }
        });


        cbCar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBetDialog4.show();
                checked_Sound.start();
            }
        });

        btnAddBetAmountCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMoney.setText(String.valueOf(refundBet(tvMoney, txtCar4Bet)));
                addBetDialog4.dismiss();
                cbCar4.setChecked(false);
                txtCar4Bet.setVisibility(View.INVISIBLE);

            }
        });

        btnAddBetAmount4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtBetAmountToAdd = addBetDialog4.findViewById(R.id.edtBetAmount);
                int result = getBetAmount(edtBetAmountToAdd, tvMoney, txtCar4Bet);
                if (result != 0) {
                    tvMoney.setText(String.valueOf(updateCurMoney(tvMoney, result)));
                    addBetDialog4.dismiss();
                    cbCar4.setChecked(true);
                    checked_Sound.start();
                    txtCar4Bet.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(main_screen.this, "Error in betting !", Toast.LENGTH_SHORT).show();
                    notification_sound.start();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bg_music != null) {
            bg_music.stop();
            bg_music.release();
        }
        if (btn_ClickSound != null) {
            btn_ClickSound.release();
        }
        if (checked_Sound != null) {
            checked_Sound.release();
        }
        if (car_race_sound != null) {
            car_race_sound.stop();
            car_race_sound.release();
        }
        if (winner_sound != null) {
            winner_sound.release();
        }
    }

    public void hideUI() {
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

    public void ShowUI() {
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
        // Stop the car race sound
        car_race_sound.stop();
        // Reset the volume of the background sound
        bg_music.setVolume(1.0f, 1.0f);
        // Play the winner sound
        winner_sound.start();
    }

    public int getBetAmount(EditText inputBet, TextView currentMoney, TextView showBet) {
        if (TextUtils.isEmpty(inputBet.getText().toString())) {
            Toast.makeText(main_screen.this, "Bet can't be empty !", Toast.LENGTH_SHORT).show();
            notification_sound.start();
            return 0;
        } else if (Integer.parseInt(inputBet.getText().toString()) > Integer.parseInt(currentMoney.getText().toString())) {
            Toast.makeText(main_screen.this, "Your bet is larger than your money !", Toast.LENGTH_SHORT).show();
            notification_sound.start();
            return 0;
        } else {
            int betAmount = Integer.parseInt(inputBet.getText().toString());
            showBet.setText(inputBet.getText());
            Toast.makeText(main_screen.this, "Bet for this car is " + betAmount, Toast.LENGTH_SHORT).show();
            return betAmount;
        }
    }

    public int updateCurMoney(TextView curMoney, int betAmount) {
        int cur = Integer.parseInt(curMoney.getText().toString());
        return cur - betAmount;
    }

    public int refundBet(TextView curMoney, TextView refundAmount) {
        int cur = Integer.parseInt(curMoney.getText().toString());
        int refund = Integer.parseInt(refundAmount.getText().toString());
        return cur + refund;
    }

    public void ResetProgress() {
        sbCar1.setProgress(0);
        sbCar2.setProgress(0);
        sbCar3.setProgress(0);
        sbCar4.setProgress(0);

        txtCar1Bet.setText("");
        txtCar2Bet.setText("");
        txtCar3Bet.setText("");
        txtCar4Bet.setText("");
        
        cbCar1.setChecked(false);
        cbCar2.setChecked(false);
        cbCar3.setChecked(false);
        cbCar4.setChecked(false);
    }
    private void ResetNghi(){
        txtCar1Bet.setText("");
        txtCar2Bet.setText("");
        txtCar3Bet.setText("");
        txtCar4Bet.setText("");

        cbCar1.setChecked(false);
        cbCar2.setChecked(false);
        cbCar3.setChecked(false);
        cbCar4.setChecked(false);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (bg_music != null) {
            bg_music.pause();
            if (isFinishing()) {
                bg_music.stop();
                bg_music.release();
            }
        }
    }
}

