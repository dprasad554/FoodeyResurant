// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.orderhistory;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrackOrderActivity_ViewBinding implements Unbinder {
  private TrackOrderActivity target;

  @UiThread
  public TrackOrderActivity_ViewBinding(TrackOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrackOrderActivity_ViewBinding(TrackOrderActivity target, View source) {
    this.target = target;

    target.vTAtoLocation = Utils.findRequiredViewAsType(source, R.id.vT_ato_location, "field 'vTAtoLocation'", TextView.class);
    target.vTAtoTime = Utils.findRequiredViewAsType(source, R.id.vT_ato_time, "field 'vTAtoTime'", TextView.class);
    target.vCAtoImage = Utils.findRequiredViewAsType(source, R.id.vC_ato_image, "field 'vCAtoImage'", CircleImageView.class);
    target.vTAtoName = Utils.findRequiredViewAsType(source, R.id.vT_ato_name, "field 'vTAtoName'", TextView.class);
    target.vTAtoPosition = Utils.findRequiredViewAsType(source, R.id.vT_ato_position, "field 'vTAtoPosition'", TextView.class);
    target.vTAtoCall = Utils.findRequiredViewAsType(source, R.id.vT_ato_call, "field 'vTAtoCall'", TextView.class);
    target.vLAtoLocation = Utils.findRequiredViewAsType(source, R.id.vL_ato_location, "field 'vLAtoLocation'", LinearLayout.class);
    target.vIAtoBack = Utils.findRequiredViewAsType(source, R.id.vI_ato_back, "field 'vIAtoBack'", ImageView.class);
    target.vTAtoHeader = Utils.findRequiredViewAsType(source, R.id.vT_ato_header, "field 'vTAtoHeader'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TrackOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vTAtoLocation = null;
    target.vTAtoTime = null;
    target.vCAtoImage = null;
    target.vTAtoName = null;
    target.vTAtoPosition = null;
    target.vTAtoCall = null;
    target.vLAtoLocation = null;
    target.vIAtoBack = null;
    target.vTAtoHeader = null;
  }
}
