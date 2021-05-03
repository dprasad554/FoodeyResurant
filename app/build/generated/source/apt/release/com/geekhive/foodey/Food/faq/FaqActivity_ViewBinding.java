// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.faq;

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

public class FaqActivity_ViewBinding implements Unbinder {
  private FaqActivity target;

  @UiThread
  public FaqActivity_ViewBinding(FaqActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FaqActivity_ViewBinding(FaqActivity target, View source) {
    this.target = target;

    target.vIAfBack = Utils.findRequiredViewAsType(source, R.id.vI_af_back, "field 'vIAfBack'", ImageView.class);
    target.vTAfHeader = Utils.findRequiredViewAsType(source, R.id.vT_af_header, "field 'vTAfHeader'", TextView.class);
    target.vTAfGeneral = Utils.findRequiredViewAsType(source, R.id.vT_af_general, "field 'vTAfGeneral'", TextView.class);
    target.vRAfList = Utils.findRequiredViewAsType(source, R.id.vR_af_list, "field 'vRAfList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FaqActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAfBack = null;
    target.vTAfHeader = null;
    target.vTAfGeneral = null;
    target.vRAfList = null;
  }
}
