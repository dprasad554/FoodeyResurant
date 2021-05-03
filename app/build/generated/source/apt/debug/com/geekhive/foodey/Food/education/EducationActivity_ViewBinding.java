// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.education;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import com.rd.PageIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EducationActivity_ViewBinding implements Unbinder {
  private EducationActivity target;

  @UiThread
  public EducationActivity_ViewBinding(EducationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EducationActivity_ViewBinding(EducationActivity target, View source) {
    this.target = target;

    target.vIAeBack = Utils.findRequiredViewAsType(source, R.id.vI_ae_back, "field 'vIAeBack'", ImageView.class);
    target.vTAeSkip = Utils.findRequiredViewAsType(source, R.id.vT_ae_skip, "field 'vTAeSkip'", TextView.class);
    target.vVAeEducational = Utils.findRequiredViewAsType(source, R.id.vV_ae_educational, "field 'vVAeEducational'", ViewPager.class);
    target.vVAePageIndicatorView = Utils.findRequiredViewAsType(source, R.id.vV_ae_pageIndicatorView, "field 'vVAePageIndicatorView'", PageIndicatorView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EducationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAeBack = null;
    target.vTAeSkip = null;
    target.vVAeEducational = null;
    target.vVAePageIndicatorView = null;
  }
}
