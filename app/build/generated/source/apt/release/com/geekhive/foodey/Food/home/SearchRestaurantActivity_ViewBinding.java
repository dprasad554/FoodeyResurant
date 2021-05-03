// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchRestaurantActivity_ViewBinding implements Unbinder {
  private SearchRestaurantActivity target;

  @UiThread
  public SearchRestaurantActivity_ViewBinding(SearchRestaurantActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchRestaurantActivity_ViewBinding(SearchRestaurantActivity target, View source) {
    this.target = target;

    target.vITlBack = Utils.findRequiredViewAsType(source, R.id.vI_tl_back, "field 'vITlBack'", ImageView.class);
    target.vTTlHeader = Utils.findRequiredViewAsType(source, R.id.vT_tl_header, "field 'vTTlHeader'", TextView.class);
    target.vTTlLocation = Utils.findRequiredViewAsType(source, R.id.vT_tl_location, "field 'vTTlLocation'", TextView.class);
    target.vITlNotification = Utils.findRequiredViewAsType(source, R.id.vI_tl_notification, "field 'vITlNotification'", ImageView.class);
    target.vLTlCount = Utils.findRequiredViewAsType(source, R.id.vL_tl_count, "field 'vLTlCount'", TextView.class);
    target.vLTlNotification = Utils.findRequiredViewAsType(source, R.id.vL_tl_notification, "field 'vLTlNotification'", LinearLayout.class);
    target.vRTlNotification = Utils.findRequiredViewAsType(source, R.id.vR_tl_notification, "field 'vRTlNotification'", RelativeLayout.class);
    target.vITlSearch = Utils.findRequiredViewAsType(source, R.id.vI_tl_cart, "field 'vITlSearch'", ImageView.class);
    target.vRTlSearch = Utils.findRequiredViewAsType(source, R.id.vR_tl_search, "field 'vRTlSearch'", RelativeLayout.class);
    target.vLTlSkip = Utils.findRequiredViewAsType(source, R.id.vL_tl_skip, "field 'vLTlSkip'", TextView.class);
    target.vTAsrCurrent = Utils.findRequiredViewAsType(source, R.id.vT_asr_current, "field 'vTAsrCurrent'", TextView.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.searchView, "field 'searchView'", SearchView.class);
    target.vRAsrList = Utils.findRequiredViewAsType(source, R.id.vR_asr_list, "field 'vRAsrList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchRestaurantActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vITlBack = null;
    target.vTTlHeader = null;
    target.vTTlLocation = null;
    target.vITlNotification = null;
    target.vLTlCount = null;
    target.vLTlNotification = null;
    target.vRTlNotification = null;
    target.vITlSearch = null;
    target.vRTlSearch = null;
    target.vLTlSkip = null;
    target.vTAsrCurrent = null;
    target.searchView = null;
    target.vRAsrList = null;
  }
}
