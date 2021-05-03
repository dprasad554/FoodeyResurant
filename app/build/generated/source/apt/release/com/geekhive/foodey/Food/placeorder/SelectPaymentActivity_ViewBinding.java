// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.placeorder;

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

public class SelectPaymentActivity_ViewBinding implements Unbinder {
  private SelectPaymentActivity target;

  @UiThread
  public SelectPaymentActivity_ViewBinding(SelectPaymentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectPaymentActivity_ViewBinding(SelectPaymentActivity target, View source) {
    this.target = target;

    target.vIAspBack = Utils.findRequiredViewAsType(source, R.id.vI_asp_back, "field 'vIAspBack'", ImageView.class);
    target.vTAspPrice = Utils.findRequiredViewAsType(source, R.id.vT_asp_price, "field 'vTAspPrice'", TextView.class);
    target.vTAspWallet = Utils.findRequiredViewAsType(source, R.id.vT_asp_wallet, "field 'vTAspWallet'", TextView.class);
    target.vRAspWallet = Utils.findRequiredViewAsType(source, R.id.vR_asp_wallet, "field 'vRAspWallet'", RecyclerView.class);
    target.vTAspOnline = Utils.findRequiredViewAsType(source, R.id.vT_asp_online, "field 'vTAspOnline'", TextView.class);
    target.vTAspOnlinesave = Utils.findRequiredViewAsType(source, R.id.vT_asp_onlinesave, "field 'vTAspOnlinesave'", TextView.class);
    target.vRAspOnline = Utils.findRequiredViewAsType(source, R.id.vR_asp_online, "field 'vRAspOnline'", RecyclerView.class);
    target.vTAspCash = Utils.findRequiredViewAsType(source, R.id.vT_asp_cash, "field 'vTAspCash'", TextView.class);
    target.vRAspCash = Utils.findRequiredViewAsType(source, R.id.vR_asp_cash, "field 'vRAspCash'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectPaymentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAspBack = null;
    target.vTAspPrice = null;
    target.vTAspWallet = null;
    target.vRAspWallet = null;
    target.vTAspOnline = null;
    target.vTAspOnlinesave = null;
    target.vRAspOnline = null;
    target.vTAspCash = null;
    target.vRAspCash = null;
  }
}
