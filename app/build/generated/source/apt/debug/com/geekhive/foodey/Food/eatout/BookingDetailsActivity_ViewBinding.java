// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

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

public class BookingDetailsActivity_ViewBinding implements Unbinder {
  private BookingDetailsActivity target;

  @UiThread
  public BookingDetailsActivity_ViewBinding(BookingDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BookingDetailsActivity_ViewBinding(BookingDetailsActivity target, View source) {
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
    target.vTAbdPname = Utils.findRequiredViewAsType(source, R.id.vT_abd_pname, "field 'vTAbdPname'", TextView.class);
    target.vTAbdPlace = Utils.findRequiredViewAsType(source, R.id.vT_abd_place, "field 'vTAbdPlace'", TextView.class);
    target.vTAbdBookingRec = Utils.findRequiredViewAsType(source, R.id.vT_abd_booking_rec, "field 'vTAbdBookingRec'", TextView.class);
    target.vTAbdConfirmBook = Utils.findRequiredViewAsType(source, R.id.vT_abd_confirm_book, "field 'vTAbdConfirmBook'", TextView.class);
    target.vTAbdConfirmed = Utils.findRequiredViewAsType(source, R.id.vT_abd_confirmed, "field 'vTAbdConfirmed'", TextView.class);
    target.vSAbdProgress = Utils.findRequiredViewAsType(source, R.id.vS_abd_progress, "field 'vSAbdProgress'", StateProgressBar.class);
    target.vTAbdUpdate = Utils.findRequiredViewAsType(source, R.id.vT_abd_update, "field 'vTAbdUpdate'", TextView.class);
    target.vTAbdName = Utils.findRequiredViewAsType(source, R.id.vT_abd_name, "field 'vTAbdName'", TextView.class);
    target.vTAbdNameval = Utils.findRequiredViewAsType(source, R.id.vT_abd_nameval, "field 'vTAbdNameval'", TextView.class);
    target.vTAbdDate = Utils.findRequiredViewAsType(source, R.id.vT_abd_date, "field 'vTAbdDate'", TextView.class);
    target.vTAbdDateval = Utils.findRequiredViewAsType(source, R.id.vT_abd_dateval, "field 'vTAbdDateval'", TextView.class);
    target.vTAbdTime = Utils.findRequiredViewAsType(source, R.id.vT_abd_time, "field 'vTAbdTime'", TextView.class);
    target.vTAbdTimeval = Utils.findRequiredViewAsType(source, R.id.vT_abd_timeval, "field 'vTAbdTimeval'", TextView.class);
    target.vTAbdNo = Utils.findRequiredViewAsType(source, R.id.vT_abd_no, "field 'vTAbdNo'", TextView.class);
    target.vTAbdNoval = Utils.findRequiredViewAsType(source, R.id.vT_abd_noval, "field 'vTAbdNoval'", TextView.class);
    target.vTAbdOrderid = Utils.findRequiredViewAsType(source, R.id.vT_abd_orderid, "field 'vTAbdOrderid'", TextView.class);
    target.vTAbdSoffer = Utils.findRequiredViewAsType(source, R.id.vT_abd_soffer, "field 'vTAbdSoffer'", TextView.class);
    target.vTAbdSofferval = Utils.findRequiredViewAsType(source, R.id.vT_abd_sofferval, "field 'vTAbdSofferval'", TextView.class);
    target.vLAbdSoffer = Utils.findRequiredViewAsType(source, R.id.vL_abd_soffer, "field 'vLAbdSoffer'", LinearLayout.class);
    target.vTAbdDirection = Utils.findRequiredViewAsType(source, R.id.vT_abd_direction, "field 'vTAbdDirection'", TextView.class);
    target.vLAbdDirection = Utils.findRequiredViewAsType(source, R.id.vL_abd_direction, "field 'vLAbdDirection'", LinearLayout.class);
    target.vTAbdViewResDetails = Utils.findRequiredViewAsType(source, R.id.vT_abd_view_res_details, "field 'vTAbdViewResDetails'", TextView.class);
    target.vLAbdViewResDetails = Utils.findRequiredViewAsType(source, R.id.vL_abd_view_res_details, "field 'vLAbdViewResDetails'", LinearLayout.class);
    target.vTAbdShareBooking = Utils.findRequiredViewAsType(source, R.id.vT_abd_share_booking, "field 'vTAbdShareBooking'", TextView.class);
    target.vLAbdShareBooking = Utils.findRequiredViewAsType(source, R.id.vL_abd_share_booking, "field 'vLAbdShareBooking'", LinearLayout.class);
    target.vTAbdCancelBooking = Utils.findRequiredViewAsType(source, R.id.vT_abd_cancel_booking, "field 'vTAbdCancelBooking'", TextView.class);
    target.vLAbdCancelBooking = Utils.findRequiredViewAsType(source, R.id.vL_abd_cancel_booking, "field 'vLAbdCancelBooking'", LinearLayout.class);
    target.vTAbdHaveQues = Utils.findRequiredViewAsType(source, R.id.vT_abd_have_ques, "field 'vTAbdHaveQues'", TextView.class);
    target.vLAbdHaveQues = Utils.findRequiredViewAsType(source, R.id.vL_abd_have_ques, "field 'vLAbdHaveQues'", LinearLayout.class);
    target.vTAbdEdit = Utils.findRequiredViewAsType(source, R.id.vT_abd_edit, "field 'vTAbdEdit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookingDetailsActivity target = this.target;
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
    target.vTAbdPname = null;
    target.vTAbdPlace = null;
    target.vTAbdBookingRec = null;
    target.vTAbdConfirmBook = null;
    target.vTAbdConfirmed = null;
    target.vSAbdProgress = null;
    target.vTAbdUpdate = null;
    target.vTAbdName = null;
    target.vTAbdNameval = null;
    target.vTAbdDate = null;
    target.vTAbdDateval = null;
    target.vTAbdTime = null;
    target.vTAbdTimeval = null;
    target.vTAbdNo = null;
    target.vTAbdNoval = null;
    target.vTAbdOrderid = null;
    target.vTAbdSoffer = null;
    target.vTAbdSofferval = null;
    target.vLAbdSoffer = null;
    target.vTAbdDirection = null;
    target.vLAbdDirection = null;
    target.vTAbdViewResDetails = null;
    target.vLAbdViewResDetails = null;
    target.vTAbdShareBooking = null;
    target.vLAbdShareBooking = null;
    target.vTAbdCancelBooking = null;
    target.vLAbdCancelBooking = null;
    target.vTAbdHaveQues = null;
    target.vLAbdHaveQues = null;
    target.vTAbdEdit = null;
  }
}
