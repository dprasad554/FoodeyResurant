package com.geekhive.foodey.Food.qrcode;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.geekhive.foodey.Food.beans.usrtakeaway.UserTakeAway;
import com.geekhive.foodey.Food.home.HomeActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;

public class QrScanActivity extends AppCompatActivity implements OnResponseListner{
    private Button button;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private final String LOGTAG = "QRCScanner-MainActivity";
    String res_id = "";
    String order_id = "";
    String cart_id = "";
    String rest_name = "";
    ConnectionDetector mDetector;
    Dialog OtpPopup, takePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);
        mDetector = new ConnectionDetector(this);
        res_id = getIntent().getStringExtra("res_id");
        order_id = getIntent().getStringExtra("order_id");
        cart_id = getIntent().getStringExtra("cart_id");
        rest_name = getIntent().getStringExtra("rest_name");

        button = (Button) findViewById(R.id.button_start_scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the qr scan activity
                Intent i = new Intent(QrScanActivity.this, QrCodeActivity.class);
                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
            }
        });

    }

    private void CallTakeAwayService(String otp) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).ConfirmTakeAway(WebServices.Foodey_Services,
                    WebServices.ApiType.otpt, cart_id, order_id, Prefs.getUserId(this), otp);
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.otpt) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                UserTakeAway userTakeAway = (UserTakeAway) response;
                if (!isSucces || userTakeAway == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (!userTakeAway.getMessage().isEmpty()){
                        if (userTakeAway.getMessage().equals("Order Receive")){
                            InitializeTakepopup();
                            initializeTakePopup();
                        } else {
                            SnackBar.makeText(this, userTakeAway.getMessage(), SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        SnackBar.makeText(this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(QrScanActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            if (result.contains(rest_name)){
                InitializeOtppopup();
                initializeOtpPopup();
            }


        }
    }

    private void InitializeOtppopup() {
        OtpPopup = new Dialog(this);
        OtpPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        OtpPopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        OtpPopup.setContentView(R.layout.otp_submit_popup);
        OtpPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        OtpPopup.setCancelable(false);
        OtpPopup.setCanceledOnTouchOutside(false);
    }

    private void initializeOtpPopup() {
        OtpPopup.setContentView(R.layout.otp_submit_popup);
        OtpPopup.setCancelable(false);
        OtpPopup.setCanceledOnTouchOutside(false);
        OtpPopup.show();

        //final TextView need_help = OtpPopup.findViewById(R.id.need_help);
        final Button submit_otp = OtpPopup.findViewById(R.id.submit_otp);
        final EditText etOtp = OtpPopup.findViewById(R.id.etOtp);
        final TextView otpId = OtpPopup.findViewById(R.id.otpId);
        otpId.setText(order_id);

        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        OtpPopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        OtpPopup.getWindow().setGravity(Gravity.BOTTOM);

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OtpPopup.isShowing()) {
                    OtpPopup.dismiss();
                    if (!etOtp.getText().toString().equals("") || !etOtp.getText().toString().isEmpty()) {
                        CallTakeAwayService(etOtp.getText().toString());
                    }
                }
            }
        });

        OtpPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        OtpPopup.setCancelable(true);
        OtpPopup.setCanceledOnTouchOutside(true);
        OtpPopup.show();
    }

    private void InitializeTakepopup() {
        takePopup = new Dialog(this);
        takePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        takePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        takePopup.setContentView(R.layout.user_order_received);
        takePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        takePopup.setCancelable(false);
        takePopup.setCanceledOnTouchOutside(false);
    }

    private void initializeTakePopup() {
        takePopup.setContentView(R.layout.user_order_received);
        takePopup.setCancelable(false);
        takePopup.setCanceledOnTouchOutside(false);
        takePopup.show();

        //final TextView need_help = OtpPopup.findViewById(R.id.need_help);
        final ImageView close_s = takePopup.findViewById(R.id.close_s);

        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
        takePopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        takePopup.getWindow().setGravity(Gravity.BOTTOM);

        close_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (takePopup.isShowing()) {
                    takePopup.dismiss();
                    startActivity(new Intent(QrScanActivity.this, HomeActivity.class));
                    QrScanActivity.this.finish();
                    /*if (!etOtp.getText().toString().equals("") || !etOtp.getText().toString().isEmpty()) {
                        CallTakeAwayService(etOtp.getText().toString());
                    }*/
                }
            }
        });

        takePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        takePopup.setCancelable(true);
        takePopup.setCanceledOnTouchOutside(true);
        takePopup.show();
    }

}
