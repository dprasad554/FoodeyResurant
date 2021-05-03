// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Grocery.activities;

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
import com.kofigyan.stateprogressbar.StateProgressBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroceryOrderSummaryActivity_ViewBinding implements Unbinder {
  private GroceryOrderSummaryActivity target;

  @UiThread
  public GroceryOrderSummaryActivity_ViewBinding(GroceryOrderSummaryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GroceryOrderSummaryActivity_ViewBinding(GroceryOrderSummaryActivity target, View source) {
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
    target.vTAosPname = Utils.findRequiredViewAsType(source, R.id.vT_aos_pname, "field 'vTAosPname'", TextView.class);
    target.vTAosPlace = Utils.findRequiredViewAsType(source, R.id.vT_aos_place, "field 'vTAosPlace'", TextView.class);
    target.vTAosBookingRec = Utils.findRequiredViewAsType(source, R.id.vT_aos_booking_rec, "field 'vTAosBookingRec'", TextView.class);
    target.vTAosConfirmBook = Utils.findRequiredViewAsType(source, R.id.vT_aos_confirm_book, "field 'vTAosConfirmBook'", TextView.class);
    target.vTAosConfirmed = Utils.findRequiredViewAsType(source, R.id.vT_aos_confirmed, "field 'vTAosConfirmed'", TextView.class);
    target.vSAosProgress = Utils.findRequiredViewAsType(source, R.id.vS_aos_progress, "field 'vSAosProgress'", StateProgressBar.class);
    target.vTAosCharges = Utils.findRequiredViewAsType(source, R.id.vT_aos_charges, "field 'vTAosCharges'", TextView.class);
    target.vTAosGrandTotal = Utils.findRequiredViewAsType(source, R.id.vT_aos_grand_total, "field 'vTAosGrandTotal'", TextView.class);
    target.vTAosGrandTotalval = Utils.findRequiredViewAsType(source, R.id.vT_aos_grand_totalval, "field 'vTAosGrandTotalval'", TextView.class);
    target.vT_order = Utils.findRequiredViewAsType(source, R.id.vT_order, "field 'vT_order'", TextView.class);
    target.vT_order_id = Utils.findRequiredViewAsType(source, R.id.vT_order_id, "field 'vT_order_id'", TextView.class);
    target.deliveryHead = Utils.findRequiredViewAsType(source, R.id.deliveryHead, "field 'deliveryHead'", TextView.class);
    target.addressText = Utils.findRequiredViewAsType(source, R.id.addressText, "field 'addressText'", TextView.class);
    target.vT_payment = Utils.findRequiredViewAsType(source, R.id.vT_payment, "field 'vT_payment'", TextView.class);
    target.vT_payment_method = Utils.findRequiredViewAsType(source, R.id.vT_payment_method, "field 'vT_payment_method'", TextView.class);
    target.vT_payment_stat = Utils.findRequiredViewAsType(source, R.id.vT_payment_stat, "field 'vT_payment_stat'", TextView.class);
    target.vT_payment_status = Utils.findRequiredViewAsType(source, R.id.vT_payment_status, "field 'vT_payment_status'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GroceryOrderSummaryActivity target = this.target;
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
    target.vTAosPname = null;
    target.vTAosPlace = null;
    target.vTAosBookingRec = null;
    target.vTAosConfirmBook = null;
    target.vTAosConfirmed = null;
    target.vSAosProgress = null;
    target.vTAosCharges = null;
    target.vTAosGrandTotal = null;
    target.vTAosGrandTotalval = null;
    target.vT_order = null;
    target.vT_order_id = null;
    target.deliveryHead = null;
    target.addressText = null;
    target.vT_payment = null;
    target.vT_payment_method = null;
    target.vT_payment_stat = null;
    target.vT_payment_status = null;
  }
}
