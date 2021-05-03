// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OTPVerificationActivity_ViewBinding implements Unbinder {
  private OTPVerificationActivity target;

  @UiThread
  public OTPVerificationActivity_ViewBinding(OTPVerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OTPVerificationActivity_ViewBinding(OTPVerificationActivity target, View source) {
    this.target = target;

    target.vTAvMobile = Utils.findRequiredViewAsType(source, R.id.vT_av_mobile, "field 'vTAvMobile'", TextView.class);
    target.vTAvMobileval = Utils.findRequiredViewAsType(source, R.id.vT_av_mobileval, "field 'vTAvMobileval'", TextView.class);
    target.vTAvOtp = Utils.findRequiredViewAsType(source, R.id.vT_av_otp, "field 'vTAvOtp'", TextView.class);
    target.vEAvOtp = Utils.findRequiredViewAsType(source, R.id.vE_av_otp, "field 'vEAvOtp'", EditText.class);
    target.vTAvOtphas = Utils.findRequiredViewAsType(source, R.id.vT_av_otphas, "field 'vTAvOtphas'", TextView.class);
    target.vTAvResend = Utils.findRequiredViewAsType(source, R.id.vT_av_resend, "field 'vTAvResend'", TextView.class);
    target.vTAvVerify = Utils.findRequiredViewAsType(source, R.id.vT_av_verify, "field 'vTAvVerify'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OTPVerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vTAvMobile = null;
    target.vTAvMobileval = null;
    target.vTAvOtp = null;
    target.vEAvOtp = null;
    target.vTAvOtphas = null;
    target.vTAvResend = null;
    target.vTAvVerify = null;
  }
}
