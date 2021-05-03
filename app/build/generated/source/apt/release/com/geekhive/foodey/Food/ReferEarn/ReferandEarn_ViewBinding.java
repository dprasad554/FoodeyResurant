// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.ReferEarn;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReferandEarn_ViewBinding implements Unbinder {
  private ReferandEarn target;

  @UiThread
  public ReferandEarn_ViewBinding(ReferandEarn target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReferandEarn_ViewBinding(ReferandEarn target, View source) {
    this.target = target;

    target.vI_afr_back = Utils.findRequiredViewAsType(source, R.id.vI_afr_back, "field 'vI_afr_back'", ImageView.class);
    target.vL_rfr_btn = Utils.findRequiredViewAsType(source, R.id.vL_rfr_btn, "field 'vL_rfr_btn'", Button.class);
    target.vL_refer_xt = Utils.findRequiredViewAsType(source, R.id.vL_refer_xt, "field 'vL_refer_xt'", TextView.class);
    target.vT_rct = Utils.findRequiredViewAsType(source, R.id.vT_rct, "field 'vT_rct'", TextView.class);
    target.referal_code = Utils.findRequiredViewAsType(source, R.id.referal_code, "field 'referal_code'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReferandEarn target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vI_afr_back = null;
    target.vL_rfr_btn = null;
    target.vL_refer_xt = null;
    target.vT_rct = null;
    target.referal_code = null;
  }
}
