package com.geekhive.foodey.Food;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.geekhive.foodey.R;

import butterknife.ButterKnife;

public class FlashMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_popup);
        ButterKnife.bind(this);

    }
}
