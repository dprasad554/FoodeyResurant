// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.viewpagerindicator.CirclePageIndicator;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EatOutActivity_ViewBinding implements Unbinder {
  private EatOutActivity target;

  @UiThread
  public EatOutActivity_ViewBinding(EatOutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EatOutActivity_ViewBinding(EatOutActivity target, View source) {
    this.target = target;

    target.vITlBack = Utils.findRequiredViewAsType(source, R.id.vI_tl_back, "field 'vITlBack'", ImageView.class);
    target.vTTlHeader = Utils.findRequiredViewAsType(source, R.id.vT_tl_header, "field 'vTTlHeader'", TextView.class);
    target.vTTlLocation = Utils.findRequiredViewAsType(source, R.id.vT_tl_location, "field 'vTTlLocation'", TextView.class);
    target.vLTlLocation = Utils.findRequiredViewAsType(source, R.id.vL_tl_location, "field 'vLTlLocation'", LinearLayout.class);
    target.vITlNotification = Utils.findRequiredViewAsType(source, R.id.vI_tl_notification, "field 'vITlNotification'", ImageView.class);
    target.vLTlCount = Utils.findRequiredViewAsType(source, R.id.vL_tl_count, "field 'vLTlCount'", TextView.class);
    target.vLTlNotification = Utils.findRequiredViewAsType(source, R.id.vL_tl_notification, "field 'vLTlNotification'", LinearLayout.class);
    target.vRTlNotification = Utils.findRequiredViewAsType(source, R.id.vR_tl_notification, "field 'vRTlNotification'", RelativeLayout.class);
    target.vITlCart = Utils.findRequiredViewAsType(source, R.id.vI_tl_cart, "field 'vITlCart'", ImageView.class);
    target.vRTlSearch = Utils.findRequiredViewAsType(source, R.id.vR_tl_search, "field 'vRTlSearch'", RelativeLayout.class);
    target.vLToolbarLayout = Utils.findRequiredViewAsType(source, R.id.vL_toolbarLayout, "field 'vLToolbarLayout'", LinearLayout.class);
    target.vTAeoBuffet = Utils.findRequiredViewAsType(source, R.id.vT_aeo_buffet, "field 'vTAeoBuffet'", TextView.class);
    target.vLAeoBuffet = Utils.findRequiredViewAsType(source, R.id.vL_aeo_buffet, "field 'vLAeoBuffet'", LinearLayout.class);
    target.vTAeoBbq = Utils.findRequiredViewAsType(source, R.id.vT_aeo_bbq, "field 'vTAeoBbq'", TextView.class);
    target.vLAeoBbq = Utils.findRequiredViewAsType(source, R.id.vL_aeo_bbq, "field 'vLAeoBbq'", LinearLayout.class);
    target.vTAeoPizza = Utils.findRequiredViewAsType(source, R.id.vT_aeo_pizza, "field 'vTAeoPizza'", TextView.class);
    target.vLAeoPizza = Utils.findRequiredViewAsType(source, R.id.vL_aeo_pizza, "field 'vLAeoPizza'", LinearLayout.class);
    target.vTAeoDrinks = Utils.findRequiredViewAsType(source, R.id.vT_aeo_drinks, "field 'vTAeoDrinks'", TextView.class);
    target.vLAeoDrinks = Utils.findRequiredViewAsType(source, R.id.vL_aeo_drinks, "field 'vLAeoDrinks'", LinearLayout.class);
    target.vTAeoShow = Utils.findRequiredViewAsType(source, R.id.vT_aeo_show, "field 'vTAeoShow'", TextView.class);
    target.vLAeoShow = Utils.findRequiredViewAsType(source, R.id.vL_aeo_show, "field 'vLAeoShow'", LinearLayout.class);
    target.vTAeoBiryani = Utils.findRequiredViewAsType(source, R.id.vT_aeo_biryani, "field 'vTAeoBiryani'", TextView.class);
    target.vLAeoBiryani = Utils.findRequiredViewAsType(source, R.id.vL_aeo_biryani, "field 'vLAeoBiryani'", LinearLayout.class);
    target.vTAeoNew = Utils.findRequiredViewAsType(source, R.id.vT_aeo_new, "field 'vTAeoNew'", TextView.class);
    target.vLAeoNew = Utils.findRequiredViewAsType(source, R.id.vL_aeo_new, "field 'vLAeoNew'", LinearLayout.class);
    target.vTAeoBudget = Utils.findRequiredViewAsType(source, R.id.vT_aeo_budget, "field 'vTAeoBudget'", TextView.class);
    target.vLAeoBudget = Utils.findRequiredViewAsType(source, R.id.vL_aeo_budget, "field 'vLAeoBudget'", LinearLayout.class);
    target.vTAeoAll = Utils.findRequiredViewAsType(source, R.id.vT_aeo_all, "field 'vTAeoAll'", TextView.class);
    target.vLAeoAll = Utils.findRequiredViewAsType(source, R.id.vL_aeo_all, "field 'vLAeoAll'", LinearLayout.class);
    target.vLAeoHide = Utils.findRequiredViewAsType(source, R.id.vL_aeo_hide, "field 'vLAeoHide'", LinearLayout.class);
    target.vRAeoList = Utils.findRequiredViewAsType(source, R.id.vR_aeo_list, "field 'vRAeoList'", RecyclerView.class);
    target.vNAeoNested = Utils.findRequiredViewAsType(source, R.id.vN_aeo_nested, "field 'vNAeoNested'", NestedScrollView.class);
    target.vVAhImage = Utils.findRequiredViewAsType(source, R.id.vV_ah_image, "field 'vVAhImage'", ViewPager.class);
    target.vCAhCircularIndicator = Utils.findRequiredViewAsType(source, R.id.vC_ah_circularIndicator, "field 'vCAhCircularIndicator'", CirclePageIndicator.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EatOutActivity target = this.target;
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
    target.vITlCart = null;
    target.vRTlSearch = null;
    target.vLToolbarLayout = null;
    target.vTAeoBuffet = null;
    target.vLAeoBuffet = null;
    target.vTAeoBbq = null;
    target.vLAeoBbq = null;
    target.vTAeoPizza = null;
    target.vLAeoPizza = null;
    target.vTAeoDrinks = null;
    target.vLAeoDrinks = null;
    target.vTAeoShow = null;
    target.vLAeoShow = null;
    target.vTAeoBiryani = null;
    target.vLAeoBiryani = null;
    target.vTAeoNew = null;
    target.vLAeoNew = null;
    target.vTAeoBudget = null;
    target.vLAeoBudget = null;
    target.vTAeoAll = null;
    target.vLAeoAll = null;
    target.vLAeoHide = null;
    target.vRAeoList = null;
    target.vNAeoNested = null;
    target.vVAhImage = null;
    target.vCAhCircularIndicator = null;
  }
}
