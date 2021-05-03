// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Grocery.activities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroceryCheckOutActivityNew_ViewBinding implements Unbinder {
  private GroceryCheckOutActivityNew target;

  @UiThread
  public GroceryCheckOutActivityNew_ViewBinding(GroceryCheckOutActivityNew target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GroceryCheckOutActivityNew_ViewBinding(GroceryCheckOutActivityNew target, View source) {
    this.target = target;

    target.checkButton = Utils.findRequiredViewAsType(source, R.id.checkButton, "field 'checkButton'", Button.class);
    target.vRAcoList = Utils.findRequiredViewAsType(source, R.id.vR_aco_list, "field 'vRAcoList'", RecyclerView.class);
    target.cartSumLin = Utils.findRequiredViewAsType(source, R.id.cartSumLin, "field 'cartSumLin'", LinearLayout.class);
    target.vT_rest_name = Utils.findRequiredViewAsType(source, R.id.vT_rest_name, "field 'vT_rest_name'", TextView.class);
    target.vT_grand_total = Utils.findRequiredViewAsType(source, R.id.vT_grand_total, "field 'vT_grand_total'", TextView.class);
    target.vT_discount = Utils.findRequiredViewAsType(source, R.id.vT_discount, "field 'vT_discount'", TextView.class);
    target.vT_aco_sub_amount = Utils.findRequiredViewAsType(source, R.id.vT_aco_sub_amount, "field 'vT_aco_sub_amount'", TextView.class);
    target.vT_taxes = Utils.findRequiredViewAsType(source, R.id.vT_taxes, "field 'vT_taxes'", TextView.class);
    target.vT_packaging = Utils.findRequiredViewAsType(source, R.id.vT_packaging, "field 'vT_packaging'", TextView.class);
    target.vT_delivery = Utils.findRequiredViewAsType(source, R.id.vT_delivery, "field 'vT_delivery'", TextView.class);
    target.vT_savings = Utils.findRequiredViewAsType(source, R.id.vT_savings, "field 'vT_savings'", TextView.class);
    target.vI_aac_summary_back = Utils.findRequiredViewAsType(source, R.id.vI_aac_summary_back, "field 'vI_aac_summary_back'", ImageView.class);
    target.removeCartItems = Utils.findRequiredViewAsType(source, R.id.removeCartItems, "field 'removeCartItems'", TextView.class);
    target.view_all = Utils.findRequiredViewAsType(source, R.id.view_all, "field 'view_all'", TextView.class);
    target.vL_savings = Utils.findRequiredViewAsType(source, R.id.vL_savings, "field 'vL_savings'", LinearLayout.class);
    target.vRAcoDeliverymode = Utils.findRequiredViewAsType(source, R.id.vR_aco_deliverymode, "field 'vRAcoDeliverymode'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GroceryCheckOutActivityNew target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.checkButton = null;
    target.vRAcoList = null;
    target.cartSumLin = null;
    target.vT_rest_name = null;
    target.vT_grand_total = null;
    target.vT_discount = null;
    target.vT_aco_sub_amount = null;
    target.vT_taxes = null;
    target.vT_packaging = null;
    target.vT_delivery = null;
    target.vT_savings = null;
    target.vI_aac_summary_back = null;
    target.removeCartItems = null;
    target.view_all = null;
    target.vL_savings = null;
    target.vRAcoDeliverymode = null;
  }
}
