// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.home;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchLocationActivity_ViewBinding implements Unbinder {
  private SearchLocationActivity target;

  @UiThread
  public SearchLocationActivity_ViewBinding(SearchLocationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchLocationActivity_ViewBinding(SearchLocationActivity target, View source) {
    this.target = target;

    target.vITlBack = Utils.findRequiredViewAsType(source, R.id.vI_tl_back, "field 'vITlBack'", ImageView.class);
    target.vTTlHeader = Utils.findRequiredViewAsType(source, R.id.vT_tl_header, "field 'vTTlHeader'", TextView.class);
    target.vTTlLocation = Utils.findRequiredViewAsType(source, R.id.vT_tl_location, "field 'vTTlLocation'", TextView.class);
    target.vLTlLocation = Utils.findRequiredViewAsType(source, R.id.vL_tl_location, "field 'vLTlLocation'", LinearLayout.class);
    target.vITlNotification = Utils.findRequiredViewAsType(source, R.id.vI_tl_notification, "field 'vITlNotification'", ImageView.class);
    target.vLTlCount = Utils.findRequiredViewAsType(source, R.id.vL_tl_count, "field 'vLTlCount'", TextView.class);
    target.vLTlNotification = Utils.findRequiredViewAsType(source, R.id.vL_tl_notification, "field 'vLTlNotification'", LinearLayout.class);
    target.vRTlNotification = Utils.findRequiredViewAsType(source, R.id.vR_tl_notification, "field 'vRTlNotification'", RelativeLayout.class);
    target.vITlSearch = Utils.findRequiredViewAsType(source, R.id.vI_tl_cart, "field 'vITlSearch'", ImageView.class);
    target.vRTlSearch = Utils.findRequiredViewAsType(source, R.id.vR_tl_search, "field 'vRTlSearch'", RelativeLayout.class);
    target.vLTlSkip = Utils.findRequiredViewAsType(source, R.id.vL_tl_skip, "field 'vLTlSkip'", TextView.class);
    target.vLToolbarLayout = Utils.findRequiredViewAsType(source, R.id.vL_toolbarLayout, "field 'vLToolbarLayout'", LinearLayout.class);
    target.vTAslcLocation = Utils.findRequiredViewAsType(source, R.id.vT_aslc_location, "field 'vTAslcLocation'", TextView.class);
    target.vEAslcSearch = Utils.findRequiredViewAsType(source, R.id.vE_aslc_search, "field 'vEAslcSearch'", EditText.class);
    target.vTAslcAuto = Utils.findRequiredViewAsType(source, R.id.vT_aslc_auto, "field 'vTAslcAuto'", TextView.class);
    target.vTAslcCurrent = Utils.findRequiredViewAsType(source, R.id.vT_aslc_current, "field 'vTAslcCurrent'", TextView.class);
    target.vRAsrList = Utils.findRequiredViewAsType(source, R.id.vR_asr_list, "field 'vRAsrList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchLocationActivity target = this.target;
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
    target.vITlSearch = null;
    target.vRTlSearch = null;
    target.vLTlSkip = null;
    target.vLToolbarLayout = null;
    target.vTAslcLocation = null;
    target.vEAslcSearch = null;
    target.vTAslcAuto = null;
    target.vTAslcCurrent = null;
    target.vRAsrList = null;
  }
}
