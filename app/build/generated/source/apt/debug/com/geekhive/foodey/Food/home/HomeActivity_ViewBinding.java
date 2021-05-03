// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
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
    target.vVAolCategoryViewpager = Utils.findRequiredViewAsType(source, R.id.vV_aol_category_viewpager, "field 'vVAolCategoryViewpager'", ViewPager.class);
    target.vIGlSearch = Utils.findRequiredViewAsType(source, R.id.vI_gl_search, "field 'vIGlSearch'", ImageView.class);
    target.vIAhHomeImage = Utils.findRequiredViewAsType(source, R.id.vI_ah_homeImage, "field 'vIAhHomeImage'", ImageView.class);
    target.vTAhHomeText = Utils.findRequiredViewAsType(source, R.id.vT_ah_homeText, "field 'vTAhHomeText'", TextView.class);
    target.vLAhHomeLayout = Utils.findRequiredViewAsType(source, R.id.vL_ah_homeLayout, "field 'vLAhHomeLayout'", LinearLayout.class);
    target.vIAhBookingImage = Utils.findRequiredViewAsType(source, R.id.vI_ah_bookingImage, "field 'vIAhBookingImage'", ImageView.class);
    target.vTAhBookingText = Utils.findRequiredViewAsType(source, R.id.vT_ah_bookingText, "field 'vTAhBookingText'", TextView.class);
    target.vLAhBookingLayout = Utils.findRequiredViewAsType(source, R.id.vL_ah_bookingLayout, "field 'vLAhBookingLayout'", LinearLayout.class);
    target.vIAhOrderImage = Utils.findRequiredViewAsType(source, R.id.vI_ah_orderImage, "field 'vIAhOrderImage'", ImageView.class);
    target.vTAhOrderText = Utils.findRequiredViewAsType(source, R.id.vT_ah_orderText, "field 'vTAhOrderText'", TextView.class);
    target.vLAhOrderLayout = Utils.findRequiredViewAsType(source, R.id.vL_ah_orderLayout, "field 'vLAhOrderLayout'", LinearLayout.class);
    target.vIAhProfileImage = Utils.findRequiredViewAsType(source, R.id.vI_ah_profileImage, "field 'vIAhProfileImage'", ImageView.class);
    target.vTAhProfileext = Utils.findRequiredViewAsType(source, R.id.vT_ah_profileext, "field 'vTAhProfileext'", TextView.class);
    target.vLAhProfileLayout = Utils.findRequiredViewAsType(source, R.id.vL_ah_profileLayout, "field 'vLAhProfileLayout'", LinearLayout.class);
    target.vVAhHome = Utils.findRequiredView(source, R.id.vV_ah_home, "field 'vVAhHome'");
    target.vVAhBooking = Utils.findRequiredView(source, R.id.vV_ah_booking, "field 'vVAhBooking'");
    target.vVAhOrder = Utils.findRequiredView(source, R.id.vV_ah_order, "field 'vVAhOrder'");
    target.vVAhProfile = Utils.findRequiredView(source, R.id.vV_ah_profile, "field 'vVAhProfile'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
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
    target.vVAolCategoryViewpager = null;
    target.vIGlSearch = null;
    target.vIAhHomeImage = null;
    target.vTAhHomeText = null;
    target.vLAhHomeLayout = null;
    target.vIAhBookingImage = null;
    target.vTAhBookingText = null;
    target.vLAhBookingLayout = null;
    target.vIAhOrderImage = null;
    target.vTAhOrderText = null;
    target.vLAhOrderLayout = null;
    target.vIAhProfileImage = null;
    target.vTAhProfileext = null;
    target.vLAhProfileLayout = null;
    target.vVAhHome = null;
    target.vVAhBooking = null;
    target.vVAhOrder = null;
    target.vVAhProfile = null;
  }
}
