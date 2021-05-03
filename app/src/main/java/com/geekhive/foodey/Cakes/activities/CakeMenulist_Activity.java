package com.geekhive.foodey.Cakes.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.geekhive.foodey.R;


public class CakeMenulist_Activity extends AppCompatActivity {

    int minteger = 0;
    int minteger1 = 0;
    int minteger2 = 0;
    int minteger3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_menulist);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.mList));
    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
    }

    public void decreaseInteger(View view) {
        if (minteger != 0) {
            minteger = minteger - 1;
            display(minteger);
        }
    }

    private void display(int number) {
        TextView displayInteger = (TextView)findViewById(R.id.tv_integerNumber);
        displayInteger.setText(String.valueOf(number));
    }

    public void increaseInteger1(View view) {
        minteger1 = minteger1 + 1;
        display1(minteger1);
    }

    public void decreaseInteger1(View view) {
        if(minteger1 !=0) {
            minteger1 = minteger1 - 1;
            display1(minteger1);
        }
    }

    private void display1(int number) {
        TextView displayInteger1 = (TextView)findViewById(R.id.tv_integerNumber1);
        displayInteger1.setText(String.valueOf(number));
    }

    public void increaseInteger2(View view) {
        minteger2 = minteger2 + 1;
        display2(minteger2);
    }

    public void decreaseInteger2(View view) {
        if (minteger2 != 0) {
            minteger2 = minteger2 - 1;
            display2(minteger2);
        }
    }

    private void display2(int number) {
        TextView displayInteger2 = (TextView)findViewById(R.id.tv_integerNumber2);
        displayInteger2.setText(String.valueOf(number));
    }

    public void increaseInteger3(View view) {
        minteger3 = minteger3 + 1;
        display3(minteger3);
    }

    public void decreaseInteger3(View view) {
        if (minteger3 != 0) {
            minteger3 = minteger3 - 1;
            display3(minteger3);
        }
    }

    private void display3(int number) {
        TextView displayInteger3 = (TextView)findViewById(R.id.tv_integerNumber3);
        displayInteger3.setText(String.valueOf(number));
    }
}
