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

public class ForgotConfirmationActivity_ViewBinding implements Unbinder {
  private ForgotConfirmationActivity target;

  @UiThread
  public ForgotConfirmationActivity_ViewBinding(ForgotConfirmationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgotConfirmationActivity_ViewBinding(ForgotConfirmationActivity target, View source) {
    this.target = target;

    target.vIAfcBack = Utils.findRequiredViewAsType(source, R.id.vI_afc_back, "field 'vIAfcBack'", ImageView.class);
    target.vTAfcForgot = Utils.findRequiredViewAsType(source, R.id.vT_afc_forgot, "field 'vTAfcForgot'", TextView.class);
    target.vTAfcMobile = Utils.findRequiredViewAsType(source, R.id.vT_afc_mobile, "field 'vTAfcMobile'", TextView.class);
    target.vTAfcStatus = Utils.findRequiredViewAsType(source, R.id.vT_afc_status, "field 'vTAfcStatus'", TextView.class);
    target.vTAfcReset = Utils.findRequiredViewAsType(source, R.id.vT_afc_reset, "field 'vTAfcReset'", TextView.class);
    target.vEAfcNewPass = Utils.findRequiredViewAsType(source, R.id.vE_afc_new_pass, "field 'vEAfcNewPass'", EditText.class);
    target.vEAfcConfirmPass = Utils.findRequiredViewAsType(source, R.id.vE_afc_confirm_pass, "field 'vEAfcConfirmPass'", EditText.class);
    target.vTAfcUpdate = Utils.findRequiredViewAsType(source, R.id.vT_afc_update, "field 'vTAfcUpdate'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotConfirmationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAfcBack = null;
    target.vTAfcForgot = null;
    target.vTAfcMobile = null;
    target.vTAfcStatus = null;
    target.vTAfcReset = null;
    target.vEAfcNewPass = null;
    target.vEAfcConfirmPass = null;
    target.vTAfcUpdate = null;
  }
}
