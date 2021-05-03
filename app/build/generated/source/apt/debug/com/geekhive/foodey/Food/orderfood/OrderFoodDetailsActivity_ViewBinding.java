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
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderFoodDetailsActivity_ViewBinding implements Unbinder {
  private OrderFoodDetailsActivity target;

  @UiThread
  public OrderFoodDetailsActivity_ViewBinding(OrderFoodDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderFoodDetailsActivity_ViewBinding(OrderFoodDetailsActivity target, View source) {
    this.target = target;

    target.vIAodImage = Utils.findRequiredViewAsType(source, R.id.vI_aod_image, "field 'vIAodImage'", ImageView.class);
    target.vTAodPhotos = Utils.findRequiredViewAsType(source, R.id.vT_aod_photos, "field 'vTAodPhotos'", TextView.class);
    target.vIAodFav = Utils.findRequiredViewAsType(source, R.id.vI_aod_fav, "field 'vIAodFav'", ImageView.class);
    target.vIAodVeg = Utils.findRequiredViewAsType(source, R.id.vI_aod_veg, "field 'vIAodVeg'", ImageView.class);
    target.vTAodVeg = Utils.findRequiredViewAsType(source, R.id.vT_aod_veg, "field 'vTAodVeg'", TextView.class);
    target.vLAodVeg = Utils.findRequiredViewAsType(source, R.id.vL_aod_veg, "field 'vLAodVeg'", LinearLayout.class);
    target.vTAodName = Utils.findRequiredViewAsType(source, R.id.vT_aod_name, "field 'vTAodName'", TextView.class);
    target.vTAodPlace = Utils.findRequiredViewAsType(source, R.id.vT_aod_place, "field 'vTAodPlace'", TextView.class);
    target.vTAodStyle = Utils.findRequiredViewAsType(source, R.id.vT_aod_style, "field 'vTAodStyle'", TextView.class);
    target.vTAodViewmap = Utils.findRequiredViewAsType(source, R.id.vT_aod_viewmap, "field 'vTAodViewmap'", TextView.class);
    target.vTAodRating = Utils.findRequiredViewAsType(source, R.id.vT_aod_rating, "field 'vTAodRating'", TextView.class);
    target.vRAodMain = Utils.findRequiredViewAsType(source, R.id.vR_aod_main, "field 'vRAodMain'", RelativeLayout.class);
    target.vTAodOffer = Utils.findRequiredViewAsType(source, R.id.vT_aod_offer, "field 'vTAodOffer'", TextView.class);
    target.vIAodBack = Utils.findRequiredViewAsType(source, R.id.vI_aod_back, "field 'vIAodBack'", ImageView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.vCAodCollapsingToolbar = Utils.findRequiredViewAsType(source, R.id.vC_aod_collapsing_toolbar, "field 'vCAodCollapsingToolbar'", CollapsingToolbarLayout.class);
    target.vAAodAppbar = Utils.findRequiredViewAsType(source, R.id.vA_aod_appbar, "field 'vAAodAppbar'", AppBarLayout.class);
    target.vVAodSlidingTabs = Utils.findRequiredViewAsType(source, R.id.vV_aod_sliding_tabs, "field 'vVAodSlidingTabs'", TabLayout.class);
    target.vVAodCategoryViewpager = Utils.findRequiredViewAsType(source, R.id.vV_aod_category_viewpager, "field 'vVAodCategoryViewpager'", ViewPager.class);
    target.vTAodPricetotal = Utils.findRequiredViewAsType(source, R.id.vT_aod_pricetotal, "field 'vTAodPricetotal'", TextView.class);
    target.vTAodItem = Utils.findRequiredViewAsType(source, R.id.vT_aod_item, "field 'vTAodItem'", TextView.class);
    target.vTAodDelivery = Utils.findRequiredViewAsType(source, R.id.vT_aod_delivery, "field 'vTAodDelivery'", TextView.class);
    target.vTAodContinue = Utils.findRequiredViewAsType(source, R.id.vT_aod_continue, "field 'vTAodContinue'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderFoodDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAodImage = null;
    target.vTAodPhotos = null;
    target.vIAodFav = null;
    target.vIAodVeg = null;
    target.vTAodVeg = null;
    target.vLAodVeg = null;
    target.vTAodName = null;
    target.vTAodPlace = null;
    target.vTAodStyle = null;
    target.vTAodViewmap = null;
    target.vTAodRating = null;
    target.vRAodMain = null;
    target.vTAodOffer = null;
    target.vIAodBack = null;
    target.toolbar = null;
    target.vCAodCollapsingToolbar = null;
    target.vAAodAppbar = null;
    target.vVAodSlidingTabs = null;
    target.vVAodCategoryViewpager = null;
    target.vTAodPricetotal = null;
    target.vTAodItem = null;
    target.vTAodDelivery = null;
    target.vTAodContinue = null;
  }
}
