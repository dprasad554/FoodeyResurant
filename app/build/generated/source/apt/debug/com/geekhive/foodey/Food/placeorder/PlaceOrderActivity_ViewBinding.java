// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.placeorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlaceOrderActivity_ViewBinding implements Unbinder {
  private PlaceOrderActivity target;

  @UiThread
  public PlaceOrderActivity_ViewBinding(PlaceOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PlaceOrderActivity_ViewBinding(PlaceOrderActivity target, View source) {
    this.target = target;

    target.vIApoBack = Utils.findRequiredViewAsType(source, R.id.vI_apo_back, "field 'vIApoBack'", ImageView.class);
    target.vTApoPrice = Utils.findRequiredViewAsType(source, R.id.vT_apo_price, "field 'vTApoPrice'", TextView.class);
    target.vTApoSave = Utils.findRequiredViewAsType(source, R.id.vT_apo_save, "field 'vTApoSave'", TextView.class);
    target.vTApoCod = Utils.findRequiredViewAsType(source, R.id.vT_apo_cod, "field 'vTApoCod'", TextView.class);
    target.vLApoCod = Utils.findRequiredViewAsType(source, R.id.vL_apo_cod, "field 'vLApoCod'", LinearLayout.class);
    target.vTApoAdd = Utils.findRequiredViewAsType(source, R.id.vT_apo_add, "field 'vTApoAdd'", TextView.class);
    target.vLApoAdd = Utils.findRequiredViewAsType(source, R.id.vL_apo_add, "field 'vLApoAdd'", LinearLayout.class);
    target.vTApoPlace = Utils.findRequiredViewAsType(source, R.id.vT_apo_place, "field 'vTApoPlace'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PlaceOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIApoBack = null;
    target.vTApoPrice = null;
    target.vTApoSave = null;
    target.vTApoCod = null;
    target.vLApoCod = null;
    target.vTApoAdd = null;
    target.vLApoAdd = null;
    target.vTApoPlace = null;
  }
}
