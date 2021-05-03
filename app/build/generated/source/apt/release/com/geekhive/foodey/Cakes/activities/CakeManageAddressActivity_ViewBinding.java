// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Cakes.activities;

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

public class CakeManageAddressActivity_ViewBinding implements Unbinder {
  private CakeManageAddressActivity target;

  @UiThread
  public CakeManageAddressActivity_ViewBinding(CakeManageAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CakeManageAddressActivity_ViewBinding(CakeManageAddressActivity target, View source) {
    this.target = target;

    target.vIAmaBack = Utils.findRequiredViewAsType(source, R.id.vI_ama_back, "field 'vIAmaBack'", ImageView.class);
    target.vTAmaManage = Utils.findRequiredViewAsType(source, R.id.vT_ama_manage, "field 'vTAmaManage'", TextView.class);
    target.vTAmaSaved = Utils.findRequiredViewAsType(source, R.id.vT_ama_saved, "field 'vTAmaSaved'", TextView.class);
    target.vRAmaList = Utils.findRequiredViewAsType(source, R.id.vR_ama_list, "field 'vRAmaList'", RecyclerView.class);
    target.vTAmaAdd = Utils.findRequiredViewAsType(source, R.id.vT_ama_add, "field 'vTAmaAdd'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CakeManageAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAmaBack = null;
    target.vTAmaManage = null;
    target.vTAmaSaved = null;
    target.vRAmaList = null;
    target.vTAmaAdd = null;
  }
}
