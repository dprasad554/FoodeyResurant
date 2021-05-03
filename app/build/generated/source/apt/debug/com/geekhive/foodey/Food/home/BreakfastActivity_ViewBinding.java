// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BreakfastActivity_ViewBinding implements Unbinder {
  private BreakfastActivity target;

  @UiThread
  public BreakfastActivity_ViewBinding(BreakfastActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BreakfastActivity_ViewBinding(BreakfastActivity target, View source) {
    this.target = target;

    target.vIAfrBack = Utils.findRequiredViewAsType(source, R.id.vI_afr_back, "field 'vIAfrBack'", ImageView.class);
    target.vTAfrHeader = Utils.findRequiredViewAsType(source, R.id.vT_afr_header, "field 'vTAfrHeader'", TextView.class);
    target.vTAfrYour = Utils.findRequiredViewAsType(source, R.id.vT_afr_your, "field 'vTAfrYour'", TextView.class);
    target.vRAfrList = Utils.findRequiredViewAsType(source, R.id.vR_afr_list, "field 'vRAfrList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BreakfastActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAfrBack = null;
    target.vTAfrHeader = null;
    target.vTAfrYour = null;
    target.vRAfrList = null;
  }
}
