package com.geekhive.foodey.Food.tablebooking;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.geekhive.foodey.R;
import com.geekhive.foodey.Food.beans.tablebooking.TableBooking;
import com.geekhive.foodey.Food.home.HomeActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableBookingActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    @BindView(R.id.et_DBName)
    EditText et_DBName;
    @BindView(R.id.et_DBMobile)
    EditText et_DBMobile;
    @BindView(R.id.alt_DBMobile)
    EditText alt_DBMobile;
    @BindView(R.id.et_DBGuests)
    EditText et_DBGuests;
    @BindView(R.id.et_DBEmail)
    EditText et_DBEmail;
    @BindView(R.id.tv_DBDate)
    TextView tv_DBDate;
    @BindView(R.id.tv_DBTime)
    TextView tv_DBTime;
    @BindView(R.id.dateLinear)
    LinearLayout dateLinear;
    @BindView(R.id.timeLinear)
    LinearLayout timeLinear;
    @BindView(R.id.vI_aac_table_back)
    ImageView vI_aac_table_back;
    @BindView(R.id.bookTable)
    Button bookTable;
    ConnectionDetector mDetector;

    String res_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_booking);
        ButterKnife.bind(this);
        setvalues();
    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        res_id = getIntent().getStringExtra("res_id");
        dateLinear.setOnClickListener(this);
        timeLinear.setOnClickListener(this);
        vI_aac_table_back.setOnClickListener(this);
        bookTable.setOnClickListener(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            if (!et_DBName.getText().toString().isEmpty() || !et_DBName.getText().toString().equals("")) {
                if (!et_DBMobile.getText().toString().isEmpty() || !et_DBMobile.getText().toString().equals("")) {
                    if (!et_DBEmail.getText().toString().isEmpty() || !et_DBEmail.getText().toString().equals("")) {
                        if (!et_DBGuests.getText().toString().isEmpty() || !et_DBGuests.getText().toString().equals("")) {
                            if (isValidMobile(et_DBMobile.getText().toString())) {
                                if (!tv_DBDate.getText().toString().isEmpty() || !tv_DBDate.getText().toString().equals("")) {
                                    if (!tv_DBTime.getText().toString().isEmpty() || !tv_DBTime.getText().toString().equals("")) {
                                        if (!alt_DBMobile.getText().toString().equals("") || !alt_DBMobile.getText().toString().isEmpty()){
                                            if (isValidMobile(alt_DBMobile.getText().toString())){
                                                new WebServices(this).TableBooking(WebServices.Foodey_Services,
                                                        WebServices.ApiType.tablebooking, Prefs.getUserId(this), et_DBName.getText().toString(), et_DBMobile.getText().toString(),
                                                        alt_DBMobile.getText().toString(), et_DBEmail.getText().toString(), et_DBGuests.getText().toString(),
                                                        tv_DBDate.getText().toString(), tv_DBTime.getText().toString(), res_id);
                                            } else {
                                                alt_DBMobile.setError("Invalid number");
                                            }

                                        }  else {
                                            alt_DBMobile.setError("Please enter mobile number");
                                        }

                                    } else {
                                        SnackBar.makeText(this, "Please choose time",SnackBar.LENGTH_SHORT).show();
                                    }
                                } else {
                                    SnackBar.makeText(this, "Please choose a date",SnackBar.LENGTH_SHORT).show();
                                }
                            } else {
                                et_DBMobile.setError("Invalid Number");
                            }
                        } else {
                            et_DBGuests.setError("Please enter guest count");
                        }
                    } else {
                        et_DBEmail.setError("Please enter email");
                    }
                } else {
                    et_DBMobile.setError("Please enter mobile number");
                }

            } else {
                et_DBName.setError("Please enter name");
            }
        } else {
            SnackBar.makeText(TableBookingActivity.this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;


    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.tablebooking) {
            if (!isSucces) {
                SnackBar.makeText(TableBookingActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final TableBooking tableBooking = (TableBooking) response;

                if (!isSucces || tableBooking == null) {
                    SnackBar.makeText(TableBookingActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(this, tableBooking.getMessage(), SnackBar.LENGTH_SHORT).show();
                    Thread timer = new Thread() {
                        public void run() {
                            try {

                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                Intent intent = new Intent(TableBookingActivity.this, HomeActivity.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                TableBookingActivity.this.finish();
                            }
                        }
                    };
                    timer.start();
                }
            }
        }
    }

    private void ShowCalender() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        if (dayOfMonth > 9) {
                            if (monthOfYear > 9) {

                                tv_DBDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            } else {
                                tv_DBDate.setText(year + "-" + ("0" + (monthOfYear + 1)) + "-" + dayOfMonth);
                            }
                        } else {
                            tv_DBDate.setText(year + "-" + ("0" + (monthOfYear + 1)) + "-" + ("0" + dayOfMonth));
                        }


                    }
                }, mYear, mMonth, mDay);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setCalendarViewShown(true);
        datePickerDialog.show();

    }

    public void setTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(TableBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tv_DBTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vI_aac_table_back:
                finish();
                break;
            case R.id.dateLinear:
                ShowCalender();
                break;
            case R.id.timeLinear:
                setTime();
                break;
            case R.id.bookTable:
                CallService();
                break;
        }
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
