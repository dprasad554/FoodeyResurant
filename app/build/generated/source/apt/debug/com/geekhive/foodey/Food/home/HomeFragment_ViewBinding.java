// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.viewpagerindicator.CirclePageIndicator;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.vRRestaurant = Utils.findRequiredViewAsType(source, R.id.vR_restaurant, "field 'vRRestaurant'", RecyclerView.class);
    target.vTRestaurant = Utils.findRequiredViewAsType(source, R.id.vT_restaurant, "field 'vTRestaurant'", TextView.class);
    target.vTRestaurantMore = Utils.findRequiredViewAsType(source, R.id.vT_restaurant_more, "field 'vTRestaurantMore'", TextView.class);
    target.vLRestaurant = Utils.findRequiredViewAsType(source, R.id.vL_restaurant, "field 'vLRestaurant'", LinearLayout.class);
    target.vRBreakfast = Utils.findRequiredViewAsType(source, R.id.vR_breakfast, "field 'vRBreakfast'", RecyclerView.class);
    target.vTBreakfast = Utils.findRequiredViewAsType(source, R.id.vT_breakfast, "field 'vTBreakfast'", TextView.class);
    target.vTBreakfastMore = Utils.findRequiredViewAsType(source, R.id.vT_breakfast_more, "field 'vTBreakfastMore'", TextView.class);
    target.vLBreakfast = Utils.findRequiredViewAsType(source, R.id.vL_breakfast, "field 'vLBreakfast'", LinearLayout.class);
    target.vTOffer = Utils.findRequiredViewAsType(source, R.id.vT_offer, "field 'vTOffer'", TextView.class);
    target.vTOfferMore = Utils.findRequiredViewAsType(source, R.id.vT_offer_more, "field 'vTOfferMore'", TextView.class);
    target.vLOffer = Utils.findRequiredViewAsType(source, R.id.vL_offer, "field 'vLOffer'", LinearLayout.class);
    target.vRSnacks = Utils.findRequiredViewAsType(source, R.id.vR_snacks, "field 'vRSnacks'", RecyclerView.class);
    target.vTSnacks = Utils.findRequiredViewAsType(source, R.id.vT_snacks, "field 'vTSnacks'", TextView.class);
    target.vTSnacksMore = Utils.findRequiredViewAsType(source, R.id.vT_snacks_more, "field 'vTSnacksMore'", TextView.class);
    target.vLSnacks = Utils.findRequiredViewAsType(source, R.id.vL_snacks, "field 'vLSnacks'", LinearLayout.class);
    target.vVAhImage = Utils.findRequiredViewAsType(source, R.id.vV_ah_image, "field 'vVAhImage'", ViewPager.class);
    target.vCAhCircularIndicator = Utils.findRequiredViewAsType(source, R.id.vC_ah_circularIndicator, "field 'vCAhCircularIndicator'", CirclePageIndicator.class);
    target.llError = Utils.findRequiredViewAsType(source, R.id.llError, "field 'llError'", LinearLayout.class);
    target.llMain = Utils.findRequiredViewAsType(source, R.id.llMain, "field 'llMain'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vRRestaurant = null;
    target.vTRestaurant = null;
    target.vTRestaurantMore = null;
    target.vLRestaurant = null;
    target.vRBreakfast = null;
    target.vTBreakfast = null;
    target.vTBreakfastMore = null;
    target.vLBreakfast = null;
    target.vTOffer = null;
    target.vTOfferMore = null;
    target.vLOffer = null;
    target.vRSnacks = null;
    target.vTSnacks = null;
    target.vTSnacksMore = null;
    target.vLSnacks = null;
    target.vVAhImage = null;
    target.vCAhCircularIndicator = null;
    target.llError = null;
    target.llMain = null;
  }
}
