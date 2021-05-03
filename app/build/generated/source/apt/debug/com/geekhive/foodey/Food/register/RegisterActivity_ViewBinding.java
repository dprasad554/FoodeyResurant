// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.register;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target, View source) {
    this.target = target;

    target.vIArBack = Utils.findRequiredViewAsType(source, R.id.vI_ar_back, "field 'vIArBack'", ImageView.class);
    target.vTArApp = Utils.findRequiredViewAsType(source, R.id.vT_ar_app, "field 'vTArApp'", TextView.class);
    target.vTArInstant = Utils.findRequiredViewAsType(source, R.id.vT_ar_instant, "field 'vTArInstant'", TextView.class);
    target.vTArNew = Utils.findRequiredViewAsType(source, R.id.vT_ar_new, "field 'vTArNew'", TextView.class);
    target.vEAfName = Utils.findRequiredViewAsType(source, R.id.vE_ar_name, "field 'vEAfName'", EditText.class);
    target.vEAfEmail = Utils.findRequiredViewAsType(source, R.id.vE_ar_email, "field 'vEAfEmail'", EditText.class);
    target.vEAfPhone = Utils.findRequiredViewAsType(source, R.id.vE_ar_phone, "field 'vEAfPhone'", EditText.class);
    target.vEAfPass = Utils.findRequiredViewAsType(source, R.id.vE_ar_pass, "field 'vEAfPass'", EditText.class);
    target.vTAfSignup = Utils.findRequiredViewAsType(source, R.id.vT_ar_signup, "field 'vTAfSignup'", TextView.class);
    target.vTAfLogin = Utils.findRequiredViewAsType(source, R.id.vT_ar_login, "field 'vTAfLogin'", TextView.class);
    target.vLAfLogin = Utils.findRequiredViewAsType(source, R.id.vL_ar_login, "field 'vLAfLogin'", LinearLayout.class);
    target.vTAfFb = Utils.findRequiredViewAsType(source, R.id.vT_ar_fb, "field 'vTAfFb'", TextView.class);
    target.vLAfFb = Utils.findRequiredViewAsType(source, R.id.vL_ar_fb, "field 'vLAfFb'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIArBack = null;
    target.vTArApp = null;
    target.vTArInstant = null;
    target.vTArNew = null;
    target.vEAfName = null;
    target.vEAfEmail = null;
    target.vEAfPhone = null;
    target.vEAfPass = null;
    target.vTAfSignup = null;
    target.vTAfLogin = null;
    target.vLAfLogin = null;
    target.vTAfFb = null;
    target.vLAfFb = null;
  }
}
