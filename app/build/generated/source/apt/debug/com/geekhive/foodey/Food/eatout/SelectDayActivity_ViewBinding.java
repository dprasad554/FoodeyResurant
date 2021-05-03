// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

import android.view.View;
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
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectDayActivity_ViewBinding implements Unbinder {
  private SelectDayActivity target;

  @UiThread
  public SelectDayActivity_ViewBinding(SelectDayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectDayActivity_ViewBinding(SelectDayActivity target, View source) {
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
    target.vLToolbarLayout = Utils.findRequiredViewAsType(source, R.id.vL_toolbarLayout, "field 'vLToolbarLayout'", LinearLayout.class);
    target.vTAsdDay = Utils.findRequiredViewAsType(source, R.id.vT_asd_day, "field 'vTAsdDay'", TextView.class);
    target.vCAsdCalendarView = Utils.findRequiredViewAsType(source, R.id.vC_asd_calendarView, "field 'vCAsdCalendarView'", HorizontalCalendarView.class);
    target.vTAsdAfter = Utils.findRequiredViewAsType(source, R.id.vT_asd_after, "field 'vTAsdAfter'", TextView.class);
    target.vIAsdAfter = Utils.findRequiredViewAsType(source, R.id.vI_asd_after, "field 'vIAsdAfter'", ImageView.class);
    target.vRAsdAfter = Utils.findRequiredViewAsType(source, R.id.vR_asd_after, "field 'vRAsdAfter'", RecyclerView.class);
    target.vTAsdEve = Utils.findRequiredViewAsType(source, R.id.vT_asd_eve, "field 'vTAsdEve'", TextView.class);
    target.vIAsdEve = Utils.findRequiredViewAsType(source, R.id.vI_asd_eve, "field 'vIAsdEve'", ImageView.class);
    target.vRAsdEve = Utils.findRequiredViewAsType(source, R.id.vR_asd_eve, "field 'vRAsdEve'", RecyclerView.class);
    target.vLAsdMain = Utils.findRequiredViewAsType(source, R.id.vL_asd_main, "field 'vLAsdMain'", LinearLayout.class);
    target.vLTlSkip = Utils.findRequiredViewAsType(source, R.id.vL_tl_skip, "field 'vLTlSkip'", TextView.class);
    target.vRAsdCalender = Utils.findRequiredViewAsType(source, R.id.vR_asd_calender, "field 'vRAsdCalender'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectDayActivity target = this.target;
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
    target.vLToolbarLayout = null;
    target.vTAsdDay = null;
    target.vCAsdCalendarView = null;
    target.vTAsdAfter = null;
    target.vIAsdAfter = null;
    target.vRAsdAfter = null;
    target.vTAsdEve = null;
    target.vIAsdEve = null;
    target.vRAsdEve = null;
    target.vLAsdMain = null;
    target.vLTlSkip = null;
    target.vRAsdCalender = null;
  }
}
