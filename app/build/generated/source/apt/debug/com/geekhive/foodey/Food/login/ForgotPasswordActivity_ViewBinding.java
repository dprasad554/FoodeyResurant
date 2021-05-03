// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgotPasswordActivity_ViewBinding implements Unbinder {
  private ForgotPasswordActivity target;

  @UiThread
  public ForgotPasswordActivity_ViewBinding(ForgotPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgotPasswordActivity_ViewBinding(ForgotPasswordActivity target, View source) {
    this.target = target;

    target.vIAfBack = Utils.findRequiredViewAsType(source, R.id.vI_af_back, "field 'vIAfBack'", ImageView.class);
    target.vTAfForgot = Utils.findRequiredViewAsType(source, R.id.vT_af_forgot, "field 'vTAfForgot'", TextView.class);
    target.vEAfMobile = Utils.findRequiredViewAsType(source, R.id.vE_af_mobile, "field 'vEAfMobile'", EditText.class);
    target.vTAfOtp = Utils.findRequiredViewAsType(source, R.id.vT_af_otp, "field 'vTAfOtp'", TextView.class);
    target.vEAfOtp = Utils.findRequiredViewAsType(source, R.id.vE_af_otp, "field 'vEAfOtp'", EditText.class);
    target.vTAfOtphas = Utils.findRequiredViewAsType(source, R.id.vT_af_otphas, "field 'vTAfOtphas'", TextView.class);
    target.vTAfResend = Utils.findRequiredViewAsType(source, R.id.vT_af_resend, "field 'vTAfResend'", TextView.class);
    target.vTAfVerify = Utils.findRequiredViewAsType(source, R.id.vT_af_verify, "field 'vTAfVerify'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAfBack = null;
    target.vTAfForgot = null;
    target.vEAfMobile = null;
    target.vTAfOtp = null;
    target.vEAfOtp = null;
    target.vTAfOtphas = null;
    target.vTAfResend = null;
    target.vTAfVerify = null;
  }
}
