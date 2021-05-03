// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.notification;

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

public class NotificationActivity_ViewBinding implements Unbinder {
  private NotificationActivity target;

  @UiThread
  public NotificationActivity_ViewBinding(NotificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NotificationActivity_ViewBinding(NotificationActivity target, View source) {
    this.target = target;

    target.vIAnBack = Utils.findRequiredViewAsType(source, R.id.vI_an_back, "field 'vIAnBack'", ImageView.class);
    target.vTAnHeader = Utils.findRequiredViewAsType(source, R.id.vT_an_header, "field 'vTAnHeader'", TextView.class);
    target.vRAnList = Utils.findRequiredViewAsType(source, R.id.vR_an_list, "field 'vRAnList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAnBack = null;
    target.vTAnHeader = null;
    target.vRAnList = null;
  }
}
