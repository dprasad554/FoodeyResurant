// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.landing;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.google.android.material.navigation.NavigationView;
import com.viewpagerindicator.CirclePageIndicator;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LandingActivity_ViewBinding implements Unbinder {
  private LandingActivity target;

  @UiThread
  public LandingActivity_ViewBinding(LandingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LandingActivity_ViewBinding(LandingActivity target, View source) {
    this.target = target;

    target.foodCard = Utils.findRequiredViewAsType(source, R.id.foodCard, "field 'foodCard'", LinearLayout.class);
    target.groceryCard = Utils.findRequiredViewAsType(source, R.id.groceryCard, "field 'groceryCard'", LinearLayout.class);
    target.cakeCard = Utils.findRequiredViewAsType(source, R.id.cakeCard, "field 'cakeCard'", LinearLayout.class);
    target.servicesCard = Utils.findRequiredViewAsType(source, R.id.servicesCard, "field 'servicesCard'", LinearLayout.class);
    target.foodText = Utils.findRequiredViewAsType(source, R.id.foodText, "field 'foodText'", TextView.class);
    target.groceryText = Utils.findRequiredViewAsType(source, R.id.groceryText, "field 'groceryText'", TextView.class);
    target.cakeText = Utils.findRequiredViewAsType(source, R.id.cakeText, "field 'cakeText'", TextView.class);
    target.servicesText = Utils.findRequiredViewAsType(source, R.id.servicesText, "field 'servicesText'", TextView.class);
    target.vVAhImage = Utils.findRequiredViewAsType(source, R.id.vV_ah_image, "field 'vVAhImage'", ViewPager.class);
    target.vCAhCircularIndicator = Utils.findRequiredViewAsType(source, R.id.vC_ah_circularIndicator, "field 'vCAhCircularIndicator'", CirclePageIndicator.class);
    target.vVAhImageDown = Utils.findRequiredViewAsType(source, R.id.vV_ah_image_down, "field 'vVAhImageDown'", ViewPager.class);
    target.vCAhCircularIndicatorDown = Utils.findRequiredViewAsType(source, R.id.vC_ah_circularIndicator_down, "field 'vCAhCircularIndicatorDown'", CirclePageIndicator.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.nav_view = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'nav_view'", NavigationView.class);
    target.drawer_layout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer_layout'", DrawerLayout.class);
    target.vTTlLocation = Utils.findRequiredViewAsType(source, R.id.vT_tl_location, "field 'vTTlLocation'", TextView.class);
    target.vLTlLocation = Utils.findRequiredViewAsType(source, R.id.vL_tl_location, "field 'vLTlLocation'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LandingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.foodCard = null;
    target.groceryCard = null;
    target.cakeCard = null;
    target.servicesCard = null;
    target.foodText = null;
    target.groceryText = null;
    target.cakeText = null;
    target.servicesText = null;
    target.vVAhImage = null;
    target.vCAhCircularIndicator = null;
    target.vVAhImageDown = null;
    target.vCAhCircularIndicatorDown = null;
    target.toolbar = null;
    target.nav_view = null;
    target.drawer_layout = null;
    target.vTTlLocation = null;
    target.vLTlLocation = null;
  }
}
