// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.selectlocation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectLocationActivity_ViewBinding implements Unbinder {
  private SelectLocationActivity target;

  @UiThread
  public SelectLocationActivity_ViewBinding(SelectLocationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectLocationActivity_ViewBinding(SelectLocationActivity target, View source) {
    this.target = target;

    target.vTAslTellus = Utils.findRequiredViewAsType(source, R.id.vT_asl_tellus, "field 'vTAslTellus'", TextView.class);
    target.vTAslThis = Utils.findRequiredViewAsType(source, R.id.vT_asl_this, "field 'vTAslThis'", TextView.class);
    target.vTAslAuto = Utils.findRequiredViewAsType(source, R.id.vT_asl_auto, "field 'vTAslAuto'", TextView.class);
    target.vTAslNo = Utils.findRequiredViewAsType(source, R.id.vT_asl_no, "field 'vTAslNo'", TextView.class);
    target.vIAslBack = Utils.findRequiredViewAsType(source, R.id.vI_asl_back, "field 'vIAslBack'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectLocationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vTAslTellus = null;
    target.vTAslThis = null;
    target.vTAslAuto = null;
    target.vTAslNo = null;
    target.vIAslBack = null;
  }
}
