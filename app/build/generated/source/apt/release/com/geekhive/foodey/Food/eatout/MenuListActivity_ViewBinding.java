// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.eatout;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MenuListActivity_ViewBinding implements Unbinder {
  private MenuListActivity target;

  @UiThread
  public MenuListActivity_ViewBinding(MenuListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MenuListActivity_ViewBinding(MenuListActivity target, View source) {
    this.target = target;

    target.vRArdMenuList = Utils.findRequiredViewAsType(source, R.id.vR_ard_menulist, "field 'vRArdMenuList'", RecyclerView.class);
    target.vIAacMenuBack = Utils.findRequiredViewAsType(source, R.id.vI_aac_menu_back, "field 'vIAacMenuBack'", ImageView.class);
    target.vTAacMenuHeader = Utils.findRequiredViewAsType(source, R.id.vT_aac_menu_header, "field 'vTAacMenuHeader'", TextView.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.searchView, "field 'searchView'", SearchView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MenuListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vRArdMenuList = null;
    target.vIAacMenuBack = null;
    target.vTAacMenuHeader = null;
    target.searchView = null;
  }
}
