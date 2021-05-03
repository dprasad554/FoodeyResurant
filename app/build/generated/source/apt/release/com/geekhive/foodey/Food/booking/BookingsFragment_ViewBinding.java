// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.booking;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookingsFragment_ViewBinding implements Unbinder {
  private BookingsFragment target;

  @UiThread
  public BookingsFragment_ViewBinding(BookingsFragment target, View source) {
    this.target = target;

    target.vVFbSlidingTabs = Utils.findRequiredViewAsType(source, R.id.vV_fb_sliding_tabs, "field 'vVFbSlidingTabs'", TabLayout.class);
    target.vVFbCategoryViewpager = Utils.findRequiredViewAsType(source, R.id.vV_fb_category_viewpager, "field 'vVFbCategoryViewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookingsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vVFbSlidingTabs = null;
    target.vVFbCategoryViewpager = null;
  }
}
