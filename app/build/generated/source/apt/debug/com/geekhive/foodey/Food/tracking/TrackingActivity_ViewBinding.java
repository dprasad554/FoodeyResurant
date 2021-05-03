// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.tracking;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrackingActivity_ViewBinding implements Unbinder {
  private TrackingActivity target;

  @UiThread
  public TrackingActivity_ViewBinding(TrackingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrackingActivity_ViewBinding(TrackingActivity target, View source) {
    this.target = target;

    target.riderInfo = Utils.findRequiredViewAsType(source, R.id.riderInfo, "field 'riderInfo'", TextView.class);
    target.callRider = Utils.findRequiredViewAsType(source, R.id.callRider, "field 'callRider'", LinearLayout.class);
    target.tv_call = Utils.findRequiredViewAsType(source, R.id.tv_call, "field 'tv_call'", TextView.class);
    target.orderETA = Utils.findRequiredViewAsType(source, R.id.orderETA, "field 'orderETA'", TextView.class);
    target.custOrderId = Utils.findRequiredViewAsType(source, R.id.orderId, "field 'custOrderId'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TrackingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.riderInfo = null;
    target.callRider = null;
    target.tv_call = null;
    target.orderETA = null;
    target.custOrderId = null;
  }
}
