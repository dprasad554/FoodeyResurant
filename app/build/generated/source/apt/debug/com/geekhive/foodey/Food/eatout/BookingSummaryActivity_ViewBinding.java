// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

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

public class BookingSummaryActivity_ViewBinding implements Unbinder {
  private BookingSummaryActivity target;

  @UiThread
  public BookingSummaryActivity_ViewBinding(BookingSummaryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BookingSummaryActivity_ViewBinding(BookingSummaryActivity target, View source) {
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
    target.vTAbsBookingSummary = Utils.findRequiredViewAsType(source, R.id.vT_abs_booking_summary, "field 'vTAbsBookingSummary'", TextView.class);
    target.vTAbsDate = Utils.findRequiredViewAsType(source, R.id.vT_abs_date, "field 'vTAbsDate'", TextView.class);
    target.vTAbsDateval = Utils.findRequiredViewAsType(source, R.id.vT_abs_dateval, "field 'vTAbsDateval'", TextView.class);
    target.vTAbsOffer = Utils.findRequiredViewAsType(source, R.id.vT_abs_offer, "field 'vTAbsOffer'", TextView.class);
    target.vTAbsOfferval = Utils.findRequiredViewAsType(source, R.id.vT_abs_offerval, "field 'vTAbsOfferval'", TextView.class);
    target.vTAbsGuest = Utils.findRequiredViewAsType(source, R.id.vT_abs_guest, "field 'vTAbsGuest'", TextView.class);
    target.vRAbsGuest = Utils.findRequiredViewAsType(source, R.id.vR_abs_guest, "field 'vRAbsGuest'", RecyclerView.class);
    target.vIAbsGuest = Utils.findRequiredViewAsType(source, R.id.vI_abs_guest, "field 'vIAbsGuest'", ImageView.class);
    target.vTAbsGuestDetail = Utils.findRequiredViewAsType(source, R.id.vT_abs_guest_detail, "field 'vTAbsGuestDetail'", TextView.class);
    target.vTAbsPhoneVerified = Utils.findRequiredViewAsType(source, R.id.vT_abs_phone_verified, "field 'vTAbsPhoneVerified'", TextView.class);
    target.vTAbsName = Utils.findRequiredViewAsType(source, R.id.vT_abs_name, "field 'vTAbsName'", TextView.class);
    target.vEAbsName = Utils.findRequiredViewAsType(source, R.id.vE_abs_name, "field 'vEAbsName'", EditText.class);
    target.vTAbsPhone = Utils.findRequiredViewAsType(source, R.id.vT_abs_phone, "field 'vTAbsPhone'", TextView.class);
    target.vEAbsPhone = Utils.findRequiredViewAsType(source, R.id.vE_abs_phone, "field 'vEAbsPhone'", EditText.class);
    target.vTAbsEmail = Utils.findRequiredViewAsType(source, R.id.vT_abs_email, "field 'vTAbsEmail'", TextView.class);
    target.vEAbsEmail = Utils.findRequiredViewAsType(source, R.id.vE_abs_email, "field 'vEAbsEmail'", EditText.class);
    target.vTAbsRequest = Utils.findRequiredViewAsType(source, R.id.vT_abs_request, "field 'vTAbsRequest'", TextView.class);
    target.vEAbsRequest = Utils.findRequiredViewAsType(source, R.id.vE_abs_request, "field 'vEAbsRequest'", EditText.class);
    target.vTAbsEnter = Utils.findRequiredViewAsType(source, R.id.vT_abs_enter, "field 'vTAbsEnter'", TextView.class);
    target.vTAbsContinue = Utils.findRequiredViewAsType(source, R.id.vT_abs_continue, "field 'vTAbsContinue'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookingSummaryActivity target = this.target;
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
    target.vTAbsBookingSummary = null;
    target.vTAbsDate = null;
    target.vTAbsDateval = null;
    target.vTAbsOffer = null;
    target.vTAbsOfferval = null;
    target.vTAbsGuest = null;
    target.vRAbsGuest = null;
    target.vIAbsGuest = null;
    target.vTAbsGuestDetail = null;
    target.vTAbsPhoneVerified = null;
    target.vTAbsName = null;
    target.vEAbsName = null;
    target.vTAbsPhone = null;
    target.vEAbsPhone = null;
    target.vTAbsEmail = null;
    target.vEAbsEmail = null;
    target.vTAbsRequest = null;
    target.vEAbsRequest = null;
    target.vTAbsEnter = null;
    target.vTAbsContinue = null;
  }
}
