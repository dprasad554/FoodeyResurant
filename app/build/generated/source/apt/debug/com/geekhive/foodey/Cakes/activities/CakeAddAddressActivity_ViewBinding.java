// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Cakes.activities;

import android.view.View;
import android.widget.EditText;
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

public class CakeAddAddressActivity_ViewBinding implements Unbinder {
  private CakeAddAddressActivity target;

  @UiThread
  public CakeAddAddressActivity_ViewBinding(CakeAddAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CakeAddAddressActivity_ViewBinding(CakeAddAddressActivity target, View source) {
    this.target = target;

    target.vIAaaBack = Utils.findRequiredViewAsType(source, R.id.vI_aaa_back, "field 'vIAaaBack'", ImageView.class);
    target.vTAaaCurrent = Utils.findRequiredViewAsType(source, R.id.vT_aaa_current, "field 'vTAaaCurrent'", TextView.class);
    target.vTAaaCurrentval = Utils.findRequiredViewAsType(source, R.id.vT_aaa_currentval, "field 'vTAaaCurrentval'", TextView.class);
    target.vIAaaHome = Utils.findRequiredViewAsType(source, R.id.vI_aaa_home, "field 'vIAaaHome'", ImageView.class);
    target.vTAaaHome = Utils.findRequiredViewAsType(source, R.id.vT_aaa_home, "field 'vTAaaHome'", TextView.class);
    target.vLAaaHome = Utils.findRequiredViewAsType(source, R.id.vL_aaa_home, "field 'vLAaaHome'", LinearLayout.class);
    target.vIAaaWork = Utils.findRequiredViewAsType(source, R.id.vI_aaa_work, "field 'vIAaaWork'", ImageView.class);
    target.vTAaaWork = Utils.findRequiredViewAsType(source, R.id.vT_aaa_work, "field 'vTAaaWork'", TextView.class);
    target.vLAaaWork = Utils.findRequiredViewAsType(source, R.id.vL_aaa_work, "field 'vLAaaWork'", LinearLayout.class);
    target.vIAaaOther = Utils.findRequiredViewAsType(source, R.id.vI_aaa_other, "field 'vIAaaOther'", ImageView.class);
    target.vTAaaOther = Utils.findRequiredViewAsType(source, R.id.vT_aaa_other, "field 'vTAaaOther'", TextView.class);
    target.vLAaaOther = Utils.findRequiredViewAsType(source, R.id.vL_aaa_other, "field 'vLAaaOther'", LinearLayout.class);
    target.vEAaaHouse = Utils.findRequiredViewAsType(source, R.id.vE_aaa_house, "field 'vEAaaHouse'", EditText.class);
    target.vEAaaApartment = Utils.findRequiredViewAsType(source, R.id.vE_aaa_apartment, "field 'vEAaaApartment'", EditText.class);
    target.vEAaaStreet = Utils.findRequiredViewAsType(source, R.id.vE_aaa_street, "field 'vEAaaStreet'", EditText.class);
    target.vEAaaArea = Utils.findRequiredViewAsType(source, R.id.vE_aaa_area, "field 'vEAaaArea'", EditText.class);
    target.vEAaaLandmark = Utils.findRequiredViewAsType(source, R.id.vE_aaa_landmark, "field 'vEAaaLandmark'", EditText.class);
    target.vEAaaCity = Utils.findRequiredViewAsType(source, R.id.vE_aaa_city, "field 'vEAaaCity'", EditText.class);
    target.vEAaaState = Utils.findRequiredViewAsType(source, R.id.vE_aaa_state, "field 'vEAaaState'", EditText.class);
    target.vEAaaPin = Utils.findRequiredViewAsType(source, R.id.vE_aaa_pin, "field 'vEAaaPin'", EditText.class);
    target.vTAaaAdd = Utils.findRequiredViewAsType(source, R.id.vT_aaa_add, "field 'vTAaaAdd'", TextView.class);
    target.vLAaaSelect = Utils.findRequiredViewAsType(source, R.id.vL_aaa_select, "field 'vLAaaSelect'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CakeAddAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vIAaaBack = null;
    target.vTAaaCurrent = null;
    target.vTAaaCurrentval = null;
    target.vIAaaHome = null;
    target.vTAaaHome = null;
    target.vLAaaHome = null;
    target.vIAaaWork = null;
    target.vTAaaWork = null;
    target.vLAaaWork = null;
    target.vIAaaOther = null;
    target.vTAaaOther = null;
    target.vLAaaOther = null;
    target.vEAaaHouse = null;
    target.vEAaaApartment = null;
    target.vEAaaStreet = null;
    target.vEAaaArea = null;
    target.vEAaaLandmark = null;
    target.vEAaaCity = null;
    target.vEAaaState = null;
    target.vEAaaPin = null;
    target.vTAaaAdd = null;
    target.vLAaaSelect = null;
  }
}
