// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Cakes.activities;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CakeAddCoupon_ViewBinding implements Unbinder {
  private CakeAddCoupon target;

  @UiThread
  public CakeAddCoupon_ViewBinding(CakeAddCoupon target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CakeAddCoupon_ViewBinding(CakeAddCoupon target, View source) {
    this.target = target;

    target.vRCouponlist = Utils.findRequiredViewAsType(source, R.id.vR_coupon_list, "field 'vRCouponlist'", RecyclerView.class);
    target.vI_cpnl_back = Utils.findRequiredViewAsType(source, R.id.vI_cpnl_back, "field 'vI_cpnl_back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CakeAddCoupon target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vRCouponlist = null;
    target.vI_cpnl_back = null;
  }
}
