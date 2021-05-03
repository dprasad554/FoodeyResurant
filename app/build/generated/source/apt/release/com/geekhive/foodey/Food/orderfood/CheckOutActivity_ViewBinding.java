// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.orderfood;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CheckOutActivity_ViewBinding implements Unbinder {
  private CheckOutActivity target;

  @UiThread
  public CheckOutActivity_ViewBinding(CheckOutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CheckOutActivity_ViewBinding(CheckOutActivity target, View source) {
    this.target = target;

    target.vTAcoCharges = Utils.findRequiredViewAsType(source, R.id.vT_aco_charges, "field 'vTAcoCharges'", TextView.class);
    target.vTAcoGrandTotal = Utils.findRequiredViewAsType(source, R.id.vT_aco_grand_total, "field 'vTAcoGrandTotal'", TextView.class);
    target.vTAcoGrandTotalval = Utils.findRequiredViewAsType(source, R.id.vT_aco_grand_totalval, "field 'vTAcoGrandTotalval'", TextView.class);
    target.vTAcoDistance = Utils.findRequiredViewAsType(source, R.id.vT_aco_distance, "field 'vTAcoDistance'", TextView.class);
    target.vTAcoViewBreak = Utils.findRequiredViewAsType(source, R.id.vT_aco_view_break, "field 'vTAcoViewBreak'", TextView.class);
    target.vTAcoSubTotal = Utils.findRequiredViewAsType(source, R.id.vT_aco_sub_total, "field 'vTAcoSubTotal'", TextView.class);
    target.vTAcoSubTotalval = Utils.findRequiredViewAsType(source, R.id.vT_aco_sub_totalval, "field 'vTAcoSubTotalval'", TextView.class);
    target.vTAcoGst = Utils.findRequiredViewAsType(source, R.id.vT_aco_gst, "field 'vTAcoGst'", TextView.class);
    target.vTAcoGstval = Utils.findRequiredViewAsType(source, R.id.vT_aco_gstval, "field 'vTAcoGstval'", TextView.class);
    target.vTAcoDelivery = Utils.findRequiredViewAsType(source, R.id.vT_aco_delivery, "field 'vTAcoDelivery'", TextView.class);
    target.vTAcoDeliveryval = Utils.findRequiredViewAsType(source, R.id.vT_aco_deliveryval, "field 'vTAcoDeliveryval'", TextView.class);
    target.vTAcoDiscount = Utils.findRequiredViewAsType(source, R.id.vT_aco_discount, "field 'vTAcoDiscount'", TextView.class);
    target.vTAcoDiscountval = Utils.findRequiredViewAsType(source, R.id.vT_aco_discountval, "field 'vTAcoDiscountval'", TextView.class);
    target.vRAcoMain = Utils.findRequiredViewAsType(source, R.id.vR_aco_main, "field 'vRAcoMain'", RelativeLayout.class);
    target.vIAcoBack = Utils.findRequiredViewAsType(source, R.id.vI_aco_back, "field 'vIAcoBack'", ImageView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.vCAcoCollapsingToolbar = Utils.findRequiredViewAsType(source, R.id.vC_aco_collapsing_toolbar, "field 'vCAcoCollapsingToolbar'", CollapsingToolbarLayout.class);
    target.vAAcoAppbar = Utils.findRequiredViewAsType(source, R.id.vA_aco_appbar, "field 'vAAcoAppbar'", AppBarLayout.class);
    target.vTAcoShop = Utils.findRequiredViewAsType(source, R.id.vT_aco_shop, "field 'vTAcoShop'", TextView.class);
    target.vTAcoLocation = Utils.findRequiredViewAsType(source, R.id.vT_aco_location, "field 'vTAcoLocation'", TextView.class);
    target.vTAcoTime = Utils.findRequiredViewAsType(source, R.id.vT_aco_time, "field 'vTAcoTime'", TextView.class);
    target.vTAcoItem = Utils.findRequiredViewAsType(source, R.id.vT_aco_item, "field 'vTAcoItem'", TextView.class);
    target.vRAcoList = Utils.findRequiredViewAsType(source, R.id.vR_aco_list, "field 'vRAcoList'", RecyclerView.class);
    target.vTAcoAdditem = Utils.findRequiredViewAsType(source, R.id.vT_aco_additem, "field 'vTAcoAdditem'", TextView.class);
    target.vTAcoCurrent = Utils.findRequiredViewAsType(source, R.id.vT_aco_current, "field 'vTAcoCurrent'", TextView.class);
    target.vTAcoCurrentval = Utils.findRequiredViewAsType(source, R.id.vT_aco_currentval, "field 'vTAcoCurrentval'", TextView.class);
    target.vTAcoAddContact = Utils.findRequiredViewAsType(source, R.id.vT_aco_add_contact, "field 'vTAcoAddContact'", TextView.class);
    target.vLAcoAddContact = Utils.findRequiredViewAsType(source, R.id.vL_aco_add_contact, "field 'vLAcoAddContact'", LinearLayout.class);
    target.vTAcoSchedule = Utils.findRequiredViewAsType(source, R.id.vT_aco_schedule, "field 'vTAcoSchedule'", TextView.class);
    target.vTAcoOrderNow = Utils.findRequiredViewAsType(source, R.id.vT_aco_order_now, "field 'vTAcoOrderNow'", TextView.class);
    target.vLAcoLocation = Utils.findRequiredViewAsType(source, R.id.vL_aco_location, "field 'vLAcoLocation'", LinearLayout.class);
    target.cartLayout = Utils.findRequiredViewAsType(source, R.id.cartLayout, "field 'cartLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckOutActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vTAcoCharges = null;
    target.vTAcoGrandTotal = null;
    target.vTAcoGrandTotalval = null;
    target.vTAcoDistance = null;
    target.vTAcoViewBreak = null;
    target.vTAcoSubTotal = null;
    target.vTAcoSubTotalval = null;
    target.vTAcoGst = null;
    target.vTAcoGstval = null;
    target.vTAcoDelivery = null;
    target.vTAcoDeliveryval = null;
    target.vTAcoDiscount = null;
    target.vTAcoDiscountval = null;
    target.vRAcoMain = null;
    target.vIAcoBack = null;
    target.toolbar = null;
    target.vCAcoCollapsingToolbar = null;
    target.vAAcoAppbar = null;
    target.vTAcoShop = null;
    target.vTAcoLocation = null;
    target.vTAcoTime = null;
    target.vTAcoItem = null;
    target.vRAcoList = null;
    target.vTAcoAdditem = null;
    target.vTAcoCurrent = null;
    target.vTAcoCurrentval = null;
    target.vTAcoAddContact = null;
    target.vLAcoAddContact = null;
    target.vTAcoSchedule = null;
    target.vTAcoOrderNow = null;
    target.vLAcoLocation = null;
    target.cartLayout = null;
  }
}
