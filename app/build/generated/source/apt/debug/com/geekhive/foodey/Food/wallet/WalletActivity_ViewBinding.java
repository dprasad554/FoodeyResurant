// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.wallet;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WalletActivity_ViewBinding implements Unbinder {
  private WalletActivity target;

  @UiThread
  public WalletActivity_ViewBinding(WalletActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WalletActivity_ViewBinding(WalletActivity target, View source) {
    this.target = target;

    target.vRTransactionlist = Utils.findRequiredViewAsType(source, R.id.vR_t_list, "field 'vRTransactionlist'", RecyclerView.class);
    target.userName = Utils.findRequiredViewAsType(source, R.id.userName, "field 'userName'", TextView.class);
    target.userEmail = Utils.findRequiredViewAsType(source, R.id.userEmail, "field 'userEmail'", TextView.class);
    target.pointsUsr = Utils.findRequiredViewAsType(source, R.id.pointsUsr, "field 'pointsUsr'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WalletActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vRTransactionlist = null;
    target.userName = null;
    target.userEmail = null;
    target.pointsUsr = null;
  }
}
