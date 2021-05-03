// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.booking;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewBookingsFragment_ViewBinding implements Unbinder {
  private NewBookingsFragment target;

  @UiThread
  public NewBookingsFragment_ViewBinding(NewBookingsFragment target, View source) {
    this.target = target;

    target.tv_DBDate = Utils.findRequiredViewAsType(source, R.id.tv_DBDate, "field 'tv_DBDate'", TextView.class);
    target.tv_DBTime = Utils.findRequiredViewAsType(source, R.id.tv_DBTime, "field 'tv_DBTime'", TextView.class);
    target.dateLinear = Utils.findRequiredViewAsType(source, R.id.dateLinear, "field 'dateLinear'", LinearLayout.class);
    target.timeLinear = Utils.findRequiredViewAsType(source, R.id.timeLinear, "field 'timeLinear'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewBookingsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_DBDate = null;
    target.tv_DBTime = null;
    target.dateLinear = null;
    target.timeLinear = null;
  }
}
