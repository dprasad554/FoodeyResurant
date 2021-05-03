// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Grocery.activities;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroceryBottomNavigationActivity_ViewBinding implements Unbinder {
  private GroceryBottomNavigationActivity target;

  @UiThread
  public GroceryBottomNavigationActivity_ViewBinding(GroceryBottomNavigationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GroceryBottomNavigationActivity_ViewBinding(GroceryBottomNavigationActivity target,
      View source) {
    this.target = target;

    target.vLTlCount = Utils.findRequiredViewAsType(source, R.id.vL_tl_count, "field 'vLTlCount'", TextView.class);
    target.vTAhHomeText = Utils.findRequiredViewAsType(source, R.id.vT_ah_homeText, "field 'vTAhHomeText'", TextView.class);
    target.vTAhBookingText = Utils.findRequiredViewAsType(source, R.id.vT_ah_bookingText, "field 'vTAhBookingText'", TextView.class);
    target.vTAhOrderText = Utils.findRequiredViewAsType(source, R.id.vT_ah_orderText, "field 'vTAhOrderText'", TextView.class);
    target.vTAhProfileext = Utils.findRequiredViewAsType(source, R.id.vT_ah_profileext, "field 'vTAhProfileext'", TextView.class);
    target.vTTlHeader = Utils.findRequiredViewAsType(source, R.id.vT_tl_header, "field 'vTTlHeader'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GroceryBottomNavigationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vLTlCount = null;
    target.vTAhHomeText = null;
    target.vTAhBookingText = null;
    target.vTAhOrderText = null;
    target.vTAhProfileext = null;
    target.vTTlHeader = null;
  }
}
