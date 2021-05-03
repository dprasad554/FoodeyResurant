// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Cakes.activities;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CakeShopSubCategoryActivity_ViewBinding implements Unbinder {
  private CakeShopSubCategoryActivity target;

  @UiThread
  public CakeShopSubCategoryActivity_ViewBinding(CakeShopSubCategoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CakeShopSubCategoryActivity_ViewBinding(CakeShopSubCategoryActivity target, View source) {
    this.target = target;

    target.resRating = Utils.findRequiredViewAsType(source, R.id.resRating, "field 'resRating'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CakeShopSubCategoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.resRating = null;
  }
}
