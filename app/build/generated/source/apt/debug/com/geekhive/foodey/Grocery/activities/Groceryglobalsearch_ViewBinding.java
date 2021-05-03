// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Grocery.activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Groceryglobalsearch_ViewBinding implements Unbinder {
  private Groceryglobalsearch target;

  @UiThread
  public Groceryglobalsearch_ViewBinding(Groceryglobalsearch target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Groceryglobalsearch_ViewBinding(Groceryglobalsearch target, View source) {
    this.target = target;

    target.searchView = Utils.findRequiredViewAsType(source, R.id.searchView, "field 'searchView'", SearchView.class);
    target.vRAsrList = Utils.findRequiredViewAsType(source, R.id.vR_g_list, "field 'vRAsrList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Groceryglobalsearch target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searchView = null;
    target.vRAsrList = null;
  }
}
