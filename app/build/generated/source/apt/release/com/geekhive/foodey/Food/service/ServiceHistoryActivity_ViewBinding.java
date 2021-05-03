// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.service;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ServiceHistoryActivity_ViewBinding implements Unbinder {
  private ServiceHistoryActivity target;

  @UiThread
  public ServiceHistoryActivity_ViewBinding(ServiceHistoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ServiceHistoryActivity_ViewBinding(ServiceHistoryActivity target, View source) {
    this.target = target;

    target.vITlBack = Utils.findRequiredViewAsType(source, R.id.vI_tl_back, "field 'vITlBack'", ImageView.class);
    target.vTTlHeader = Utils.findRequiredViewAsType(source, R.id.vT_tl_header, "field 'vTTlHeader'", TextView.class);
    target.vTTlLocation = Utils.findRequiredViewAsType(source, R.id.vT_tl_location, "field 'vTTlLocation'", TextView.class);
    target.vLTlLocation = Utils.findRequiredViewAsType(source, R.id.vL_tl_location, "field 'vLTlLocation'", LinearLayout.class);
    target.vITlNotification = Utils.findRequiredViewAsType(source, R.id.vI_tl_notification, "field 'vITlNotification'", ImageView.class);
    target.vLTlCount = Utils.findRequiredViewAsType(source, R.id.vL_tl_count, "field 'vLTlCount'", TextView.class);
    target.vLTlNotification = Utils.findRequiredViewAsType(source, R.id.vL_tl_notification, "field 'vLTlNotification'", LinearLayout.class);
    target.vRTlNotification = Utils.findRequiredViewAsType(source, R.id.vR_tl_notification, "field 'vRTlNotification'", RelativeLayout.class);
    target.vITlCart = Utils.findRequiredViewAsType(source, R.id.vI_tl_cart, "field 'vITlCart'", ImageView.class);
    target.vRTlSearch = Utils.findRequiredViewAsType(source, R.id.vR_tl_search, "field 'vRTlSearch'", RelativeLayout.class);
    target.vLTlSkip = Utils.findRequiredViewAsType(source, R.id.vL_tl_skip, "field 'vLTlSkip'", TextView.class);
    target.vRGLSearch = Utils.findRequiredViewAsType(source, R.id.vR_gl_search, "field 'vRGLSearch'", RelativeLayout.class);
    target.vLToolbarLayout = Utils.findRequiredViewAsType(source, R.id.vL_toolbarLayout, "field 'vLToolbarLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ServiceHistoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vITlBack = null;
    target.vTTlHeader = null;
    target.vTTlLocation = null;
    target.vLTlLocation = null;
    target.vITlNotification = null;
    target.vLTlCount = null;
    target.vLTlNotification = null;
    target.vRTlNotification = null;
    target.vITlCart = null;
    target.vRTlSearch = null;
    target.vLTlSkip = null;
    target.vRGLSearch = null;
    target.vLToolbarLayout = null;
  }
}
