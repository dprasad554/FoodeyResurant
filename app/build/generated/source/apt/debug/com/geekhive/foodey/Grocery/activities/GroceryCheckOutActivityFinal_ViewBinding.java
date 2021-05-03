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

public class GroceryCheckOutActivityFinal_ViewBinding implements Unbinder {
  private GroceryCheckOutActivityFinal target;

  @UiThread
  public GroceryCheckOutActivityFinal_ViewBinding(GroceryCheckOutActivityFinal target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GroceryCheckOutActivityFinal_ViewBinding(GroceryCheckOutActivityFinal target,
      View source) {
    this.target = target;

    target.payButton = Utils.findRequiredViewAsType(source, R.id.payButton, "field 'payButton'", Button.class);
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
    target.addressSelected = Utils.findRequiredViewAsType(source, R.id.addressSelected, "field 'addressSelected'", TextView.class);
    target.vT_edit = Utils.findRequiredViewAsType(source, R.id.vT_edit, "field 'vT_edit'", TextView.class);
    target.vT_coupon_txt = Utils.findRequiredViewAsType(source, R.id.vT_coupon_txt, "field 'vT_coupon_txt'", TextView.class);
    target.vL_coupon_layout = Utils.findRequiredViewAsType(source, R.id.vL_coupon_layout, "field 'vL_coupon_layout'", LinearLayout.class);
    target.vT_instructions_txt = Utils.findRequiredViewAsType(source, R.id.vT_instructions_txt, "field 'vT_instructions_txt'", TextView.class);
    target.vL_instruction = Utils.findRequiredViewAsType(source, R.id.vL_instruction, "field 'vL_instruction'", LinearLayout.class);
    target.instruction_layout = Utils.findRequiredViewAsType(source, R.id.instruction_layout, "field 'instruction_layout'", LinearLayout.class);
    target.vT_coupon_try = Utils.findRequiredViewAsType(source, R.id.vT_coupon_try, "field 'vT_coupon_try'", TextView.class);
    target.vT_instructions = Utils.findRequiredViewAsType(source, R.id.vT_instructions, "field 'vT_instructions'", TextView.class);
    target.vT_coupon = Utils.findRequiredViewAsType(source, R.id.vT_coupon, "field 'vT_coupon'", TextView.class);
    target.vL_savings = Utils.findRequiredViewAsType(source, R.id.vL_savings, "field 'vL_savings'", LinearLayout.class);
    target.vT_offer_discount = Utils.findRequiredViewAsType(source, R.id.vT_offer_discount, "field 'vT_offer_discount'", TextView.class);
    target.vT_remove = Utils.findRequiredViewAsType(source, R.id.vT_remove, "field 'vT_remove'", TextView.class);
    target.vL_coupon_l = Utils.findRequiredViewAsType(source, R.id.vL_coupon_l, "field 'vL_coupon_l'", LinearLayout.class);
    target.vI_instruction = Utils.findRequiredViewAsType(source, R.id.vI_instruction, "field 'vI_instruction'", ImageView.class);
    target.vL_offer_discount = Utils.findRequiredViewAsType(source, R.id.vL_offer_discount, "field 'vL_offer_discount'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GroceryCheckOutActivityFinal target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.payButton = null;
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
    target.addressSelected = null;
    target.vT_edit = null;
    target.vT_coupon_txt = null;
    target.vL_coupon_layout = null;
    target.vT_instructions_txt = null;
    target.vL_instruction = null;
    target.instruction_layout = null;
    target.vT_coupon_try = null;
    target.vT_instructions = null;
    target.vT_coupon = null;
    target.vL_savings = null;
    target.vT_offer_discount = null;
    target.vT_remove = null;
    target.vL_coupon_l = null;
    target.vI_instruction = null;
    target.vL_offer_discount = null;
  }
}
