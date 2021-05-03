// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.login;

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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.vIAlBack = Utils.findRequiredViewAsType(source, R.id.vI_al_back, "field 'vIAlBack'", ImageView.class);
    target.vTAlApp = Utils.findRequiredViewAsType(source, R.id.vT_al_app, "field 'vTAlApp'", TextView.class);
    target.vTAlInstant = Utils.findRequiredViewAsType(source, R.id.vT_al_instant, "field 'vTAlInstant'", TextView.class);
    target.vTAlLogin = Utils.findRequiredViewAsType(source, R.id.vT_al_login, "field 'vTAlLogin'", TextView.class);
    target.vEAlMobile = Utils.findRequiredViewAsType(source, R.id.vE_al_Mobile, "field 'vEAlMobile'", EditText.class);
    target.vIAlPass = Utils.findRequiredViewAsType(source, R.id.vI_al_pass, "field 'vIAlPass'", ImageView.class);
    target.vEAlPass = Utils.findRequiredViewAsType(source, R.id.vE_al_pass, "field 'vEAlPass'", EditText.class);
    target.vTAlForgot = Utils.findRequiredViewAsType(source, R.id.vT_al_forgot, "field 'vTAlForgot'", TextView.class);
    target.vTAlLogina = Utils.findRequiredViewAsType(source, R.id.vT_al_logina, "field 'vTAlLogina'", TextView.class);
    target.vTAlNewUser = Utils.findRequiredViewAsType(source, R.id.vT_al_new_user, "field 'vTAlNewUser'", TextView.class);
    target.vTAlSignup = Utils.findRequiredViewAsType(source, R.id.vT_al_signup, "field 'vTAlSignup'", TextView.class);
    target.vLAlSignup = Utils.findRequiredViewAsType(source, R.id.vL_al_signup, "field 'vLAlSignup'", LinearLayout.class);
    target.vTAlFb = Utils.findRequiredViewAsType(source, R.id.vT_al_fb, "field 'vTAlFb'", TextView.class);
    target.vLAlFb = Utils.findRequiredViewAsType(source, R.id.vL_al_fb, "field 'vLAlFb'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAlBack = null;
    target.vTAlApp = null;
    target.vTAlInstant = null;
    target.vTAlLogin = null;
    target.vEAlMobile = null;
    target.vIAlPass = null;
    target.vEAlPass = null;
    target.vTAlForgot = null;
    target.vTAlLogina = null;
    target.vTAlNewUser = null;
    target.vTAlSignup = null;
    target.vLAlSignup = null;
    target.vTAlFb = null;
    target.vLAlFb = null;
  }
}
