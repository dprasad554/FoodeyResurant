// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RestaurantDetailsActivityCopy_ViewBinding implements Unbinder {
  private RestaurantDetailsActivityCopy target;

  @UiThread
  public RestaurantDetailsActivityCopy_ViewBinding(RestaurantDetailsActivityCopy target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RestaurantDetailsActivityCopy_ViewBinding(RestaurantDetailsActivityCopy target,
      View source) {
    this.target = target;

    target.vIArdBack = Utils.findRequiredViewAsType(source, R.id.vI_ard_back, "field 'vIArdBack'", ImageView.class);
    target.vRArdMain = Utils.findRequiredViewAsType(source, R.id.vR_ard_main, "field 'vRArdMain'", LinearLayout.class);
    target.vTArdName = Utils.findRequiredViewAsType(source, R.id.vT_ard_name, "field 'vTArdName'", TextView.class);
    target.vTArdStatus = Utils.findRequiredViewAsType(source, R.id.vT_ard_status, "field 'vTArdStatus'", TextView.class);
    target.vTArdPlace = Utils.findRequiredViewAsType(source, R.id.vT_ard_place, "field 'vTArdPlace'", TextView.class);
    target.vTArdTiming = Utils.findRequiredViewAsType(source, R.id.vT_ard_timing, "field 'vTArdTiming'", TextView.class);
    target.vTArdStyle = Utils.findRequiredViewAsType(source, R.id.vT_ard_style, "field 'vTArdStyle'", TextView.class);
    target.vTArdViewmap = Utils.findRequiredViewAsType(source, R.id.vT_ard_viewmap, "field 'vTArdViewmap'", TextView.class);
    target.vTArdBook = Utils.findRequiredViewAsType(source, R.id.vT_ard_book, "field 'vTArdBook'", TextView.class);
    target.vROffer = Utils.findRequiredViewAsType(source, R.id.vR_offer, "field 'vROffer'", RecyclerView.class);
    target.vISearch = Utils.findRequiredViewAsType(source, R.id.vI_search, "field 'vISearch'", ImageView.class);
    target.vS_sb = Utils.findRequiredViewAsType(source, R.id.vS_sb, "field 'vS_sb'", SwitchCompat.class);
    target.vT_st = Utils.findRequiredViewAsType(source, R.id.vT_st, "field 'vT_st'", TextView.class);
    target.delTime = Utils.findRequiredViewAsType(source, R.id.del_time, "field 'delTime'", TextView.class);
    target.resRating = Utils.findRequiredViewAsType(source, R.id.resRating, "field 'resRating'", TextView.class);
    target.llResOffer = Utils.findRequiredViewAsType(source, R.id.llResOffer, "field 'llResOffer'", LinearLayout.class);
    target.appCost = Utils.findRequiredViewAsType(source, R.id.appCost, "field 'appCost'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RestaurantDetailsActivityCopy target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIArdBack = null;
    target.vRArdMain = null;
    target.vTArdName = null;
    target.vTArdStatus = null;
    target.vTArdPlace = null;
    target.vTArdTiming = null;
    target.vTArdStyle = null;
    target.vTArdViewmap = null;
    target.vTArdBook = null;
    target.vROffer = null;
    target.vISearch = null;
    target.vS_sb = null;
    target.vT_st = null;
    target.delTime = null;
    target.resRating = null;
    target.llResOffer = null;
    target.appCost = null;
  }
}
