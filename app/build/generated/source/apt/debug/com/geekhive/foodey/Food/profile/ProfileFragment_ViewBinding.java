// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.profile;

import android.view.View;
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

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  @UiThread
  public ProfileFragment_ViewBinding(ProfileFragment target, View source) {
    this.target = target;

    target.vIFpImage = Utils.findRequiredViewAsType(source, R.id.vI_fp_image, "field 'vIFpImage'", ImageView.class);
    target.vTFpName = Utils.findRequiredViewAsType(source, R.id.vT_fp_name, "field 'vTFpName'", TextView.class);
    target.vTFpNumber = Utils.findRequiredViewAsType(source, R.id.vT_fp_number, "field 'vTFpNumber'", TextView.class);
    target.vTFpMail = Utils.findRequiredViewAsType(source, R.id.vT_fp_mail, "field 'vTFpMail'", TextView.class);
    target.vTFpEdit = Utils.findRequiredViewAsType(source, R.id.vT_fp_edit, "field 'vTFpEdit'", TextView.class);
    target.vTFpManage = Utils.findRequiredViewAsType(source, R.id.vT_fp_manage, "field 'vTFpManage'", TextView.class);
    target.vLFpManage = Utils.findRequiredViewAsType(source, R.id.vL_fp_manage, "field 'vLFpManage'", LinearLayout.class);
    target.vTFpAbout = Utils.findRequiredViewAsType(source, R.id.vT_fp_about, "field 'vTFpAbout'", TextView.class);
    target.vLFpAbout = Utils.findRequiredViewAsType(source, R.id.vL_fp_about, "field 'vLFpAbout'", LinearLayout.class);
    target.vTFpContactUs = Utils.findRequiredViewAsType(source, R.id.vT_fp_contactus, "field 'vTFpContactUs'", TextView.class);
    target.vLFpContactUs = Utils.findRequiredViewAsType(source, R.id.vL_fp_contactus, "field 'vLFpContactUs'", LinearLayout.class);
    target.vTFpPrivacy = Utils.findRequiredViewAsType(source, R.id.vT_fp_privacy, "field 'vTFpPrivacy'", TextView.class);
    target.vLFpPrivacy = Utils.findRequiredViewAsType(source, R.id.vL_fp_privacy, "field 'vLFpPrivacy'", LinearLayout.class);
    target.vTFpRefund = Utils.findRequiredViewAsType(source, R.id.vT_fp_refund, "field 'vTFpRefund'", TextView.class);
    target.vLFpRefund = Utils.findRequiredViewAsType(source, R.id.vL_fp_refund, "field 'vLFpRefund'", LinearLayout.class);
    target.vTFpTerms = Utils.findRequiredViewAsType(source, R.id.vT_fp_terms, "field 'vTFpTerms'", TextView.class);
    target.vLFpTerms = Utils.findRequiredViewAsType(source, R.id.vL_fp_terms, "field 'vLFpTerms'", LinearLayout.class);
    target.vTFpDisclaimer = Utils.findRequiredViewAsType(source, R.id.vT_fp_disclaimer, "field 'vTFpDisclaimer'", TextView.class);
    target.vLFpDisclaimer = Utils.findRequiredViewAsType(source, R.id.vL_fp_disclaimer, "field 'vLFpDisclaimer'", LinearLayout.class);
    target.vTFpSignout = Utils.findRequiredViewAsType(source, R.id.vT_fp_signout, "field 'vTFpSignout'", TextView.class);
    target.vLFpSignout = Utils.findRequiredViewAsType(source, R.id.vL_fp_signout, "field 'vLFpSignout'", LinearLayout.class);
    target.vLFpCart = Utils.findRequiredViewAsType(source, R.id.vL_fp_cart, "field 'vLFpCart'", LinearLayout.class);
    target.vTFpCart = Utils.findRequiredViewAsType(source, R.id.vT_fp_cart, "field 'vTFpCart'", TextView.class);
    target.vCallBtn = Utils.findRequiredViewAsType(source, R.id.i_call, "field 'vCallBtn'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIFpImage = null;
    target.vTFpName = null;
    target.vTFpNumber = null;
    target.vTFpMail = null;
    target.vTFpEdit = null;
    target.vTFpManage = null;
    target.vLFpManage = null;
    target.vTFpAbout = null;
    target.vLFpAbout = null;
    target.vTFpContactUs = null;
    target.vLFpContactUs = null;
    target.vTFpPrivacy = null;
    target.vLFpPrivacy = null;
    target.vTFpRefund = null;
    target.vLFpRefund = null;
    target.vTFpTerms = null;
    target.vLFpTerms = null;
    target.vTFpDisclaimer = null;
    target.vLFpDisclaimer = null;
    target.vTFpSignout = null;
    target.vLFpSignout = null;
    target.vLFpCart = null;
    target.vTFpCart = null;
    target.vCallBtn = null;
  }
}
