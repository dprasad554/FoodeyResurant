// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.placeorder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddCardActivity_ViewBinding implements Unbinder {
  private AddCardActivity target;

  @UiThread
  public AddCardActivity_ViewBinding(AddCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddCardActivity_ViewBinding(AddCardActivity target, View source) {
    this.target = target;

    target.vIAacBack = Utils.findRequiredViewAsType(source, R.id.vI_aac_back, "field 'vIAacBack'", ImageView.class);
    target.vTAacHeader = Utils.findRequiredViewAsType(source, R.id.vT_aac_header, "field 'vTAacHeader'", TextView.class);
    target.vTAacAccept = Utils.findRequiredViewAsType(source, R.id.vT_aac_accept, "field 'vTAacAccept'", TextView.class);
    target.vEAacName = Utils.findRequiredViewAsType(source, R.id.vE_aac_name, "field 'vEAacName'", EditText.class);
    target.vTAacAdd = Utils.findRequiredViewAsType(source, R.id.vT_aac_add, "field 'vTAacAdd'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAacBack = null;
    target.vTAacHeader = null;
    target.vTAacAccept = null;
    target.vEAacName = null;
    target.vTAacAdd = null;
  }
}
