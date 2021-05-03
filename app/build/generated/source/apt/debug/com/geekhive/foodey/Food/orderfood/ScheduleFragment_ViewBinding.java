// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.orderfood;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScheduleFragment_ViewBinding implements Unbinder {
  private ScheduleFragment target;

  @UiThread
  public ScheduleFragment_ViewBinding(ScheduleFragment target, View source) {
    this.target = target;

    target.vRFuList = Utils.findRequiredViewAsType(source, R.id.vR_fu_list, "field 'vRFuList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ScheduleFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vRFuList = null;
  }
}
